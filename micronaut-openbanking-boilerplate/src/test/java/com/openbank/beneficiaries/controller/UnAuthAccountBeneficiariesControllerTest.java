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
class UnAuthAccountBeneficiariesControllerTest {

    private static final Logger log = LoggerFactory.getLogger(UnAuthAccountBeneficiariesControllerTest.class.getName());

    public static String username = "test";
    public static String password = "test001";

    @BeforeAll
    public static void setupServer () {

        TestServer.setupServer();
    }

    @AfterAll
    public static void stopServer (){

        TestServer.stopServer();
    }

    @Test
    void getAccountBeneficiaries () throws JsonProcessingException {

        String url = "/account/222918878/beneficiaries";
        String body= TestServer
                .getHttpClient()
                .toBlocking()
                .retrieve(HttpRequest.GET(url));

        log.info("# getAccountBeneficiaries () {} : " + body);
    }

    @Test
    public void getAccountBeneficiaries_accountIdEmpty () {

        String url = "/account/beneficiaries";
        HttpClientResponseException e = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .GET(url)
                            .contentType(MediaType.APPLICATION_JSON), String.class);

            log.info("# getAccountBeneficiaries () {} : Body" + String.valueOf(body.getBody().get()));
            log.info("# getAccountBeneficiaries () {} : status" + String.valueOf(body.getStatus().getCode()));

        });

        log.info("# getAccountBeneficiaries () {} : Body" + String.valueOf(e.getResponse().getBody().get()));
        log.info("# getAccountBeneficiaries () {} : status" + String.valueOf(e.getStatus().getCode()));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.getCode(), e.getStatus().getCode());
    }

}