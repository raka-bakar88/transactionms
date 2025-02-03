package com.challenge.transactionms.messaging;

import com.challenge.transactionms.model.AuthenticationRequest;
import com.challenge.transactionms.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {
    private static final Logger logger = LoggerFactory.getLogger(MessageProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void authorize(String request) {
        kafkaTemplate.send(AppConstants.AUTH_REQ_TOPIC, request);
        logger.info("Send Kafka topic : {}", AppConstants.AUTH_REQ_TOPIC);
        logger.info("Send Kafka message : {}", request);
    }
}
