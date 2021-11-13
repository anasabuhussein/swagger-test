package com.openbank.beneficiaries.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openbank.beneficiaries.TestServer;
import com.openbank.beneficiaries.model.OBBeneficiary5;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.sql.SQLException;

@Disabled
class OBBeneficiary5KafkaProducerServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(OBBeneficiary5KafkaProducerServiceImplTest.class.getName());

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

    public OBBeneficiary5KafkaProducerServiceImpl getBeneficiary5KafkaProducerService () {

        OBBeneficiary5KafkaProducerServiceImpl beneficiary5KafkaProducerService = TestServer
                .getApplicationContext()
                .getBean(OBBeneficiary5KafkaProducerServiceImpl.class);

        return  beneficiary5KafkaProducerService;
    }

    @Test
    @Disabled
    void createOBBeneficiaryWithKafkaProducer() {

        try{

            getBeneficiary5KafkaProducerService()
                    .createOBBeneficiaryWithKafkaProducer("TEST_001",
                            "Ben5",
                            getData());

        }catch (IOException e) {
            log.info(String.valueOf(e.getCause()
                    .fillInStackTrace()
                    .getCause()
                    .fillInStackTrace()
                    .getCause()));
        }
    }

    public OBBeneficiary5 getData () {

        OBBeneficiary5 obBeneficiary;
        obBeneficiary = OBBeneficiary5.builder()
                .beneficiaryId("Ben5")
                .accountId("22289")
                .reference("Towbar Club")
//                .beneficiaryType(OBBeneficiaryType1Code.Trusted)
//                .creditorAccount(OBCashAccount5.builder()
//                        .identification("80200112345678")
//                        .name("Mrs Juniper")
//                        .schemeName(OBExternalAccountIdentification4Code.BICFI)
//                        .build())
                .build();

        return obBeneficiary;
    }
}