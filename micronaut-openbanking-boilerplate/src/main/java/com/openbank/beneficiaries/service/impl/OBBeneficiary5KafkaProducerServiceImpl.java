package com.openbank.beneficiaries.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openbank.beneficiaries.kafka.OBBeneficiary5MessageProducer;
import com.openbank.beneficiaries.model.OBBeneficiary5;
import com.openbank.beneficiaries.service.OBBeneficiary5KafkaProducerService;
import io.micronaut.messaging.exceptions.MessagingClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;


/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

@Singleton
public class OBBeneficiary5KafkaProducerServiceImpl implements OBBeneficiary5KafkaProducerService {


    private final static Logger log = LoggerFactory
            .getLogger(OBBeneficiary5KafkaProducerServiceImpl
                    .class
                    .getName());

    private final OBBeneficiary5MessageProducer<String> beneficiary5MessageProducer;
    private final OBBeneficiary5ServiceImpl beneficiary5Service;

    public OBBeneficiary5KafkaProducerServiceImpl(OBBeneficiary5MessageProducer<String> beneficiary5MessageProducer,
                                                  OBBeneficiary5ServiceImpl beneficiary5Service) {

        this.beneficiary5MessageProducer = beneficiary5MessageProducer;
        this.beneficiary5Service = beneficiary5Service;
    }

    @Override
    public OBBeneficiary5 createOBBeneficiaryWithKafkaProducer(String topic,
                                                               String key,
                                                               OBBeneficiary5 beneficiary)
            throws NoSuchElementException, PersistenceException, MessagingClientException, JsonProcessingException{

        log.info("# createOBBeneficiaryWithKafkaProducer {}");
        log.info("# try to create OBBeneficiary5 object");
        beneficiary = beneficiary5Service.createBeneficiary(beneficiary);

        log.info("# Success OBBeneficiary5 created ...");
        log.info("# send data to kafka ...");
        beneficiary5MessageProducer.sendBeneficiary(topic,
                key,
                new ObjectMapper().writeValueAsString(beneficiary));

        log.info("# send data to kafka successfully...");

        return beneficiary;
    }

    @Override
    public OBBeneficiary5 updateOBBeneficiaryWithKafkaProducer(String topic,
                                                               String key,
                                                               String beneficiaryId,
                                                               OBBeneficiary5 beneficiary)
            throws  NoSuchElementException, PersistenceException, MessagingClientException, JsonProcessingException {

        log.info("# updateOBBeneficiaryWithKafkaProducer {}");
        log.info("# try to update OBBeneficiary5 object");
        beneficiary = beneficiary5Service.updateBeneficiary(beneficiaryId, beneficiary);

        log.info("# Success OBBeneficiary5 updated ...");
        log.info("# send data to kafka ...");
        beneficiary5MessageProducer.sendBeneficiary(topic,
                key,
                new ObjectMapper().writeValueAsString(beneficiary));

        log.info("# send data to kafka successfully...");

        return beneficiary;
    }
}
