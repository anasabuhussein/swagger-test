package com.openbank.beneficiaries.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openbank.beneficiaries.TestServer;
import com.openbank.beneficiaries.model.OBBeneficiary5;
import com.openbank.beneficiaries.model.OBBeneficiaryType1Code;
import com.openbank.beneficiaries.model.OBCashAccount5;
import com.openbank.beneficiaries.model.OBExternalAccountIdentification4Code;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.NoSuchElementException;


@Disabled
@MicronautTest
class OBBeneficiary5ServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(OBBeneficiary5ServiceImplTest.class.getName());

    @BeforeAll
    public static void setupServer () {
        TestServer.setupServer();
    }

    @AfterAll
    public static void stopServer (){
        TestServer.stopServer();
    }

    public OBBeneficiary5ServiceImpl getBeneficiaryService () {

        OBBeneficiary5ServiceImpl beneficiary5Service = TestServer
                .getApplicationContext()
                .getBean(OBBeneficiary5ServiceImpl.class);

        return  beneficiary5Service;
    }

    public OBBeneficiary5 getData () {

        OBBeneficiary5 obBeneficiary;
        obBeneficiary = OBBeneficiary5.builder()
                .beneficiaryId("Ben5")
                .accountId("222810")
                .reference("Towbar Club")
                .beneficiaryType(OBBeneficiaryType1Code.Trusted)
                .creditorAccount(OBCashAccount5.builder()
                        .identification("80200112345678")
                        .name("Mrs Juniper")
                        .schemeName(OBExternalAccountIdentification4Code.BICFI)
                        .build())
                .build();

        return obBeneficiary;
    }

    @Test
    @Disabled
    void findAll() throws JsonProcessingException {

        try {

            System.out.println(new ObjectMapper()
                    .writeValueAsString(getBeneficiaryService()
                            .findAll()));

        }catch (IOException e){
            log.info(String.valueOf(e.getCause()
                    .fillInStackTrace()
                    .getCause()
                    .fillInStackTrace()
                    .getCause()));
        }
    }

    @Test
    @Disabled
    void findByAccountId() throws JsonProcessingException {


        try {
            System.out.println(new ObjectMapper()
                    .writeValueAsString(getBeneficiaryService()
                            .findByAccountId("22289", null)));

        }catch (IOException e){
            log.info(String.valueOf(e.getCause()
                    .fillInStackTrace()
                    .getCause()
                    .fillInStackTrace()
                    .getCause()));
        }
    }

    @Test
    @Disabled
    void findByBeneficiaryId() throws JsonProcessingException {


        try {
            System.out.println(new ObjectMapper()
                    .writeValueAsString(getBeneficiaryService()
                            .findByBeneficiaryId("Ben9")));

        }catch (NoSuchElementException e){
            log.info(e.getMessage());
        }
    }

    @Test
    @Disabled
    void createBeneficiary() {


        try {
            System.out.println(new ObjectMapper()
                    .writeValueAsString(getBeneficiaryService()
                            .createBeneficiary(getData())));

        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
        } catch (PersistenceException e){
            log.info("Error ::: " + String.valueOf(e
                    .fillInStackTrace()
                    .getCause()
                    .fillInStackTrace()
                    .getCause()));

        }
    }

    @Test
    @Disabled
    void updateBeneficiary() throws JsonProcessingException {


        try {
            System.out.println(new ObjectMapper()
                    .writeValueAsString(getBeneficiaryService()
                            .updateBeneficiary(getData())));

        }catch (IOException e){
            log.info(String.valueOf(e.getCause()
                    .fillInStackTrace()
                    .getCause()
                    .fillInStackTrace()
                    .getCause()));
        }
    }

    @Test
    @Disabled
    void deleteBeneficiaryById() {

        try {
            getBeneficiaryService().deleteBeneficiaryById("Ben5");
//        } catch (NoSuchElementException e){
//            log.info(e.getMessage());
        } catch (PersistenceException | NoSuchElementException e){
            log.info("Error : " + String.valueOf(e.fillInStackTrace()
                    .getCause()
                    .fillInStackTrace()
                    .getCause()));
        }
    }
}