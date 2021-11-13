package com.openbank.beneficiaries.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openbank.beneficiaries.model.OBBeneficiary5;
import io.micronaut.messaging.exceptions.MessagingClientException;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

public interface OBBeneficiary5KafkaProducerService {

    public OBBeneficiary5 createOBBeneficiaryWithKafkaProducer (String topic,
                                                                String key,
                                                                OBBeneficiary5 beneficiary)
            throws NoSuchElementException, PersistenceException, MessagingClientException, JsonProcessingException;

    public OBBeneficiary5 updateOBBeneficiaryWithKafkaProducer (String topic,
                                                                String key,
                                                                String beneficiaryId,
                                                                OBBeneficiary5 beneficiary)
            throws  NoSuchElementException, PersistenceException, MessagingClientException, JsonProcessingException;
}
