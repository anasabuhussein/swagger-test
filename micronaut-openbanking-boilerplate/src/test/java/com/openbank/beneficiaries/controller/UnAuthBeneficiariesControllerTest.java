package com.openbank.beneficiaries.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openbank.beneficiaries.TestServer;
import com.openbank.beneficiaries.model.*;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Disabled
@MicronautTest
class UnAuthBeneficiariesControllerTest {


    private static final Logger log = LoggerFactory.getLogger(UnAuthBeneficiariesControllerTest.class.getName());


    @BeforeAll
    public static void setupServer () {

        TestServer.setupServer();
    }

    @AfterAll
    public static void stopServer (){

        TestServer.stopServer();
    }

    @Test
    void getBeneficiariesWithoutPage() {

        String body= TestServer
                .getHttpClient()
                .toBlocking()
                .retrieve(HttpRequest.GET(getBeneficiariesUrl(null)), String.class);

        log.info("# getBeneficiariesWithoutPage () {}: " + body);
    }

    @Test
    void getBeneficiariesWithPage_0() {

        String body = TestServer
                .getHttpClient()
                .toBlocking()
                .retrieve(HttpRequest.GET(getBeneficiariesUrl("0")), String.class);

        log.info("# getBeneficiariesWithPage_0 () {}: " + body);
    }

    @Test
    void getBeneficiariesWithPage_1() {

        String body = TestServer
                .getHttpClient()
                .toBlocking()
                .retrieve(HttpRequest.GET(getBeneficiariesUrl("1")), String.class);

        log.info("# getBeneficiariesWithPage_1 () {}: " + body);
    }

    @Test
    void createBeneficiary() {

        BearerAccessRefreshToken accessToken;
        String ben = "Ben100";
        String caIdentification = "80200112345300000";
        String accountId = "222918878";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        try{

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
                            .contentType(MediaType.APPLICATION_JSON), String.class);

            log.info("# createBeneficiary () {} " + body.getBody().get());
        }catch (Exception e) {
            log.info("# createBeneficiary () {} " + e.getLocalizedMessage());
        }
    }

    @Test
    void createBeneficiaryAllData() {

        BearerAccessRefreshToken accessToken;
        String ben = "Ben101";
        String caIdentification = "80200112345300000";
        String accountId = "222918870";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        try{

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
                    .creditorAgent(OBBranchAndFinancialInstitutionIdentification6.builder()
                            .identification(caIdentification)
                            .name("Towbar Club")
                            .schemeName(OBExternalFinancialInstitutionIdentification4Code.BICFI)
                            .postalAddress(OBPostalAddress6.builder()
                                    .addressLine("Jordan")
                                    .townName("Amman")
                                    .build())
                            .build())
                    .build();

            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .POST(getBeneficiaryUrl(null, kafkaTopic, kafkaKey),
                                    obBeneficiary)
                            .contentType(MediaType.APPLICATION_JSON), String.class);

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
                            .contentType(MediaType.APPLICATION_JSON), String.class);

            log.info("# createBeneficiaryWithoutKafkaTopicAndKey () {}: " + body.getBody().get());
        }catch (Exception e) {
            log.info("# createBeneficiaryWithoutKafkaTopicAndKey () {}: " + e.getLocalizedMessage());
        }

    }

    @Test
    void createBeneficiaryWithConstraintViolationException() {

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
                            .contentType(MediaType.APPLICATION_JSON), String.class);

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
        String accountId = "222910000008878";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        try{

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
                            .contentType(MediaType.APPLICATION_JSON), String.class);

            log.info("# updateBeneficiary () {} " + body.getBody().get());
        }catch (Exception e) {
            log.info("# updateBeneficiary () {} " + e.getLocalizedMessage());
        }

    }

    @Test
    void updateBeneficiaryWithNullCreditor() {

        BearerAccessRefreshToken accessToken;
        String ben = "Ben1";
        String caIdentification = "80200112345300000";
        String accountId = "222918878";
        String kafkaTopic = "TEST_001";
        String kafkaKey = ben;

        try{

            OBBeneficiary5 obBeneficiary = OBBeneficiary5.builder()
                    .beneficiaryId(ben)
                    .accountId(accountId)
                    .reference("Towbar Club !! Updated")
                    .beneficiaryType(OBBeneficiaryType1Code.Trusted)
                    .creditorAccount(null)
                    .build();

            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .PUT(getBeneficiaryUrl(ben, kafkaTopic, kafkaKey),
                                    obBeneficiary)
                            .contentType(MediaType.APPLICATION_JSON), String.class);

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
                            .contentType(MediaType.APPLICATION_JSON), String.class);

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

            HttpResponse body = TestServer
                    .getHttpClient()
                    .toBlocking()
                    .exchange(HttpRequest
                            .DELETE(getBeneficiaryUrl(ben, null , null))
                            .contentType(MediaType.APPLICATION_JSON), String.class);

            log.info("# deleteBeneficiary () {} " + body.getBody().get());
        }catch (Exception e) {
            log.info("# deleteBeneficiary () {} " + e.getLocalizedMessage());
        }

    }

    @Test
    void deleteBeneficiaryNotFoundRecord() {

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
                            .contentType(MediaType.APPLICATION_JSON), String.class);
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