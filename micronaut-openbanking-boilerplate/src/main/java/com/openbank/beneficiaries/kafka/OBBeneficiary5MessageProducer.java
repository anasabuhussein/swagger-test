package com.openbank.beneficiaries.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;


/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

@KafkaClient
public interface OBBeneficiary5MessageProducer<T> {

    public void sendBeneficiary(@Topic String topic,
                     @KafkaKey String brand,
                     T object);
}
