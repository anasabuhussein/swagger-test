package com.openbank.beneficiaries.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openbank.beneficiaries.TestServer;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Disabled
class AuthAccountBeneficiariesControllerTest {

    private static final Logger log = LoggerFactory.getLogger(AuthAccountBeneficiariesControllerTest.class.getName());

    @BeforeAll
    public static void setupServer () {

        TestServer.setupServer();
    }

    @AfterAll
    public static void stopServer (){

        TestServer.stopServer();
    }

    @Test
    void unauthorizedError() {

        HttpClientResponseException e = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .GET("/account/22291/beneficiaries")
                            .contentType(MediaType.APPLICATION_JSON), String.class);

            log.info("# unauthorizedError () {} : Body" + String.valueOf(body.getBody().get()));
            log.info("# unauthorizedError () {} : status " + String.valueOf(body.getStatus().getCode()));

        });

        log.info("# unauthorizedError () {} : Body" + String.valueOf(e.getResponse().getBody()));
        log.info("# unauthorizedError () {} : status " + String.valueOf(e.getStatus().getCode()));
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.getCode(), e.getStatus().getCode());

    }

    @Test
    void getAccountBeneficiariesWithReadDetails () throws JsonProcessingException {

        String username = "test@test123.com";
        String password = "test001";

        BearerAccessRefreshToken accessToken = login(username, password);

        String body= TestServer
                .getHttpClient()
                .toBlocking()
                .retrieve(HttpRequest.GET("/account/22291/beneficiaries")
                        .bearerAuth(accessToken.getAccessToken()));

        log.info(body);
    }

    @Test
    void getAccountBeneficiariesWithoutReadDetails () throws JsonProcessingException {

        String username = "test1@test123.com";
        String password = "test001";

        BearerAccessRefreshToken accessToken = login(username, password);

        String body= TestServer
                .getHttpClient()
                .toBlocking()
                .retrieve(HttpRequest.GET("/account/22291/beneficiaries")
                        .bearerAuth(accessToken.getAccessToken()));

        log.info(body);
    }

    BearerAccessRefreshToken login(String name, String password) throws JsonProcessingException {


        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(name, password);
        HttpRequest request = HttpRequest.POST("/login", creds);

        HttpResponse<BearerAccessRefreshToken> rsp;
        rsp = TestServer
                .getHttpClient().toBlocking()
                .exchange(request, BearerAccessRefreshToken.class);

        return rsp.getBody().get();

    }

}