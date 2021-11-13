package com.openbank.beneficiaries.jwt;

import com.openbank.beneficiaries.model.User;
import com.openbank.beneficiaries.service.impl.UserServiceImpl;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;

    public UserAuthenticationProvider(UserServiceImpl userService) {
        super();
        this.userService = userService;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {

        System.out.println(" --- 1 ---");
        System.out.println(httpRequest.getBody().get());
        System.out.println(authenticationRequest.getIdentity());
        System.out.println(authenticationRequest.getSecret());


        return Flowable.create(emitter -> {

            System.out.println(" --- 2 ---");
            Object identity = authenticationRequest.getIdentity();
            Object secret = authenticationRequest.getSecret();

            User user = findUserFromDB(identity);

            if (user != null && secret.equals(user.getPassword())) {

                System.out.println(" --- 3 ---");

                emitter.onNext(new UserDetails((String) identity,
                        Collections.singletonList(user.getPrivileges().name())));
                emitter.onComplete();
                return;
            }

            System.out.println(" --- 4 ---");
            emitter.onError(new AuthenticationException(new AuthenticationFailed("Wrong username or password")));
        }, BackpressureStrategy.ERROR);
    }

    public void test(HttpRequest httpRequest) {
        System.out.println(httpRequest.getBody());
    }

    public User findUserFromDB(Object identity) {

        return userService.findUser(String.valueOf(identity));
    }

}
