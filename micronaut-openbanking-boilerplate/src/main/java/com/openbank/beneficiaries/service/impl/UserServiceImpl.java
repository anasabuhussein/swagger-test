package com.openbank.beneficiaries.service.impl;

import com.openbank.beneficiaries.model.User;
import com.openbank.beneficiaries.repository.UserRepository;
import com.openbank.beneficiaries.service.UserService;
import io.micronaut.transaction.annotation.ReadOnly;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Singleton
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findUser(String username) throws NoSuchElementException {

        User user = userRepository.findById(username).orElse(null);
        return user;
    }
}
