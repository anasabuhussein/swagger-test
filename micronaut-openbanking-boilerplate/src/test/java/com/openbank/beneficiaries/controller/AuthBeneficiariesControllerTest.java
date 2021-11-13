package com.openbank.beneficiaries.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openbank.beneficiaries.TestServer;
import com.openbank.beneficiaries.model.OBBeneficiary5;
import com.openbank.beneficiaries.model.OBBeneficiaryType1Code;
import com.openbank.beneficiaries.model.OBCashAccount5;
import com.openbank.beneficiaries.model.OBExternalAccountIdentification4Code;
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
class AuthBeneficiariesControllerTest {

    private static final Logger log = LoggerFactory.getLogger(AuthBeneficiariesControllerTest.class.getName());

    public static String username = "test@test123.com";
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
    void unauthorizedError() {

        HttpClientResponseException e = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .GET(getBeneficiaryUrl(null, null , null))
                            .contentType(MediaType.APPLICATION_JSON), String.class);

            log.info("# unauthorizedError () {} : Body" + String.valueOf(body.getBody().get()));
            log.info("# unauthorizedError () {} : status" + String.valueOf(body.getStatus().getCode()));

        });

        log.info("# unauthorizedError () {} : Body" + String.valueOf(e.getResponse().getBody()));
        log.info("# unauthorizedError () {} : status" + String.valueOf(e.getStatus().getCode()));

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.getCode(), e.getStatus().getCode());

    }

    @Test
    void getBeneficiariesWithoutPage() {

        BearerAccessRefreshToken accessToken = login(username, password);
        String body= TestServer
                .getHttpClient()
                .toBlocking()
                .retrieve(HttpRequest.GET(getBeneficiariesUrl(null))
                        .bearerAuth(accessToken.getAccessToken()), String.class);

        log.info("# getBeneficiariesWithoutPage () {}: " + body);
    }

    @Test
    void getBeneficiariesWithPage_0() {

        BearerAccessRefreshToken accessToken = login(username, password);
        String body = TestServer
                .getHttpClient()
                .toBlocking()
                .retrieve(HttpRequest.GET(getBeneficiariesUrl("0"))
                        .bearerAuth(accessToken.getAccessToken()), String.class);

        log.info("# getBeneficiariesWithPage_0 () {}: " + body);
    }

    @Test
    void getBeneficiariesWithPage_1() {

        BearerAccessRefreshToken accessToken = login(username, password);
        String body = TestServer
                .getHttpClient()
                .toBlocking()
                .retrieve(HttpRequest.GET(getBeneficiariesUrl("1"))
                        .bearerAuth(accessToken.getAccessToken()), String.class);

        log.info("# getBeneficiariesWithPage_1 () {}: " + body);
    }

    @Test
    void createBeneficiary() {

        BearerAccessRefreshToken accessToken;
        String ben = "Ben200";
        String caIdentification = "80200112345300000";
        String accountId = "222918878";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        try{
            accessToken = login(username, password);

            OBBeneficiary5 obBeneficiary = OBBeneficiary5.builder()
                    .beneficiaryId(ben)
                    .accountId(accountId)
                    .reference("Towbar Club")
                    .beneficiaryType(OBBeneficiaryType1Code.Trusted)
                    .creditorAccount(OBCashAccount5.builder()
                            .identification(caIdentification)
                            .name("Mrs Juniper")
                            .schemeName(OBExternalAccountIdentification4Code.SortCodeAccountNumber)
                            .build())
                    .build();

            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                                    .POST(getBeneficiaryUrl(null, kafkaTopic, kafkaKey),
                                            obBeneficiary)
                                    .contentType(MediaType.APPLICATION_JSON)
                            .bearerAuth(accessToken.getAccessToken()), String.class);

            log.info("# createBeneficiary () {} " + body.getBody().get());
        }catch (Exception e) {
            log.info("# createBeneficiary () {} " + e.getLocalizedMessage());
        }
    }

    @Test
    void createBeneficiaryWithCreditorAccount() {

        BearerAccessRefreshToken accessToken;
        String ben = "Ben201";
        String caIdentification = "80200112345300000";
        String accountId = "222918870";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        try{
            accessToken = login(username, password);

            OBBeneficiary5 obBeneficiary = OBBeneficiary5.builder()
                    .beneficiaryId(ben)
                    .accountId(accountId)
                    .reference("Towbar Club")
                    .beneficiaryType(OBBeneficiaryType1Code.Trusted)
                    .creditorAccount(OBCashAccount5.builder()
                            .identification(caIdentification)
                            .name("Mrs Juniper")
                            .schemeName(OBExternalAccountIdentification4Code.SortCodeAccountNumber)
                            .build())
                    .build();

            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .POST(getBeneficiaryUrl(null, kafkaTopic, kafkaKey),
                                    obBeneficiary)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bearerAuth(accessToken.getAccessToken()), String.class);

            log.info("# createBeneficiaryWithCreditorAccount () {} " + body.getBody().get());
        }catch (Exception e) {
            log.info("# createBeneficiaryWithCreditorAccount () {} " + e.getLocalizedMessage());
        }
    }

    @Test
    void createBeneficiaryWithoutKafkaTopicAndKey() throws JsonProcessingException {

        BearerAccessRefreshToken accessToken;
        String ben = "Ben3";
        String caIdentification = "80200112345300000";
        String accountId = "222918870";
        String kafkaTopic = null;
        String kafkaKey = null;

        try{
            accessToken = login(username, password);

            OBBeneficiary5 obBeneficiary = OBBeneficiary5.builder()
                    .beneficiaryId(ben)
                    .accountId(accountId)
                    .reference("Towbar Club")
                    .beneficiaryType(OBBeneficiaryType1Code.Trusted)
                    .creditorAccount(OBCashAccount5.builder()
                            .identification(caIdentification)
                            .name("Mrs Juniper")
                            .schemeName(OBExternalAccountIdentification4Code.SortCodeAccountNumber)
                            .build())
                    .build();

            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .POST(getBeneficiaryUrl(null, kafkaTopic, kafkaKey),
                                    obBeneficiary)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bearerAuth(accessToken.getAccessToken()), String.class);

            log.info("# createBeneficiaryWithoutKafkaTopicAndKey () {} " + body.getBody().get());
        }catch (Exception e) {
            log.info("# createBeneficiaryWithoutKafkaTopicAndKey () {} " + e.getLocalizedMessage());
        }

    }

    @Test
    void createBeneficiaryWithConstraintViolationException() {

        BearerAccessRefreshToken accessToken = login(username, password);

        String ben = "";
        String caIdentification = "80200112345300000";
        String accountId = "222918870";
        String kafkaTopic = null;
        String kafkaKey = null;

        OBBeneficiary5 obBeneficiary = OBBeneficiary5.builder()
                .beneficiaryId(ben)
                .accountId(accountId)
                .reference("Towbar Club")
                .beneficiaryType(OBBeneficiaryType1Code.Trusted)
                .creditorAccount(OBCashAccount5.builder()
                        .identification(caIdentification)
                        .name("Mrs Juniper")
                        .schemeName(OBExternalAccountIdentification4Code.SortCodeAccountNumber)
                        .build())
                .build();

        HttpClientResponseException e = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .POST(getBeneficiaryUrl(null, null, null),
                                    obBeneficiary)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bearerAuth(accessToken.getAccessToken()), String.class);

            log.info("# createBeneficiaryWithConstraintViolationException () {} : Body" + String.valueOf(body.getBody().get()));
            log.info("# createBeneficiaryWithConstraintViolationException () {} : status" + String.valueOf(body.getStatus().getCode()));

        });

        log.info("# createBeneficiaryWithConstraintViolationException () {} : Body" + String.valueOf(e.getResponse().getBody().get()));
        log.info("# createBeneficiaryWithConstraintViolationException () {} : status" + String.valueOf(e.getStatus().getCode()));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.getCode(), e.getStatus().getCode());
    }

    @Test
    void updateBeneficiary() {

        BearerAccessRefreshToken accessToken;
        String ben = "Ben1";
        String caIdentification = "80200112345300000";
        String accountId = "222918878";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        try{
            accessToken = login(username, password);

            OBBeneficiary5 obBeneficiary = OBBeneficiary5.builder()
                    .beneficiaryId(ben)
                    .accountId(accountId)
                    .reference("Towbar Club !! Updated")
                    .beneficiaryType(OBBeneficiaryType1Code.Trusted)
                    .creditorAccount(OBCashAccount5.builder()
                            .identification(caIdentification)
                            .name("Mrs Juniper")
                            .schemeName(OBExternalAccountIdentification4Code.SortCodeAccountNumber)
                            .build())
                    .build();

            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .PUT(getBeneficiaryUrl(ben, kafkaTopic, kafkaKey),
                                    obBeneficiary)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bearerAuth(accessToken.getAccessToken()), String.class);

            log.info("# updateBeneficiary () {} " + body.getBody().get());
        }catch (Exception e) {
            log.info("# updateBeneficiary () {} " + e.getLocalizedMessage());
        }

    }

    @Test
    void updateBeneficiaryWithConstraintViolationException() {

        BearerAccessRefreshToken accessToken;
        String ben = "";
        String caIdentification = "80200112345300000";
        String accountId = "222918878";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        try{
            accessToken = login(username, password);

            OBBeneficiary5 obBeneficiary = OBBeneficiary5.builder()
                    .beneficiaryId(ben)
                    .accountId(accountId)
                    .reference("Towbar Club !! Updated")
                    .beneficiaryType(OBBeneficiaryType1Code.Trusted)
                    .creditorAccount(OBCashAccount5.builder()
                            .identification(caIdentification)
                            .name("Mrs Juniper")
                            .schemeName(OBExternalAccountIdentification4Code.SortCodeAccountNumber)
                            .build())
                    .build();

            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .PUT(getBeneficiaryUrl(ben, kafkaTopic, kafkaKey),
                                    obBeneficiary)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bearerAuth(accessToken.getAccessToken()), String.class);

            log.info("# updateBeneficiaryWithConstraintViolationException () {} " + body.getBody().get());
        }catch (Exception e) {
            log.info("# updateBeneficiaryWithConstraintViolationException () {} " + e.getLocalizedMessage());
        }

    }

    @Test
    void deleteBeneficiary() {

        BearerAccessRefreshToken accessToken;
        String ben = "Ben1";
        String caIdentification = "80200112345300000";
        String accountId = "222918878";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        try{

            accessToken = login(username, password);

            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .DELETE(getBeneficiaryUrl(ben, null , null))
                            .contentType(MediaType.APPLICATION_JSON)
                            .bearerAuth(accessToken.getAccessToken()), String.class);

            log.info("# deleteBeneficiary () {} " + body.getBody().get());
        }catch (Exception e) {
            log.info("# deleteBeneficiary () {} " + e.getLocalizedMessage());
        }

    }

    @Test
    void deleteBeneficiaryNotFoundRecord() {

        BearerAccessRefreshToken accessToken = login(username, password);
        String ben = "Ben1";
        String caIdentification = "80200112345300000";
        String accountId = "222918878";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        HttpClientResponseException e = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .DELETE(getBeneficiaryUrl(ben, null , null))
                            .contentType(MediaType.APPLICATION_JSON)
                            .bearerAuth(accessToken.getAccessToken()), String.class);

            log.info("# deleteBeneficiaryNotFoundRecord () {}: Body: " + body.getBody().get());
            log.info("# deleteBeneficiaryNotFoundRecord () {}: Status: " + body.getStatus().getCode());
        });

        log.info("# deleteBeneficiaryNotFoundRecord () {}: Body: " + e.getResponse().getBody());
        log.info("# deleteBeneficiaryNotFoundRecord () {}: Status: " + e.getResponse().getStatus().getCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.getCode(), e.getStatus().getCode());
    }

    BearerAccessRefreshToken login(String username, String password) {

        HttpResponse<BearerAccessRefreshToken> rsp;

        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(username, password);
        HttpRequest request = HttpRequest.POST("/login", creds);

        rsp = TestServer
                .getHttpClient().toBlocking()
                .exchange(request, BearerAccessRefreshToken.class);

        return rsp.getBody().get();

    }


    public String getBeneficiariesUrl(String page) {
        String url = "/beneficiaries";
        if(page != null)
            return url + "?page=" +page;

        return url;
    }

    public String getBeneficiaryUrl (String beneficiaryId, String topic, String key) {
        String url = "/beneficiary";
        if (beneficiaryId != null && (topic != null && key != null))
            return url +"/" + beneficiaryId + "?topic=" + topic + "&key=" + key;

        if (topic != null && key != null)
            return url + "?topic=" + topic + "&key=" + key;


        if (beneficiaryId != null)
            return url + "/" + beneficiaryId;

        return url;
    }
}