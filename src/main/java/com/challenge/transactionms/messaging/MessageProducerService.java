package com.challenge.transactionms.messaging;

import com.challenge.transactionms.model.MessageRequest;
import com.challenge.transactionms.utils.AppConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public void authorize(MessageRequest request) throws JsonProcessingException {
        String messageJson = new ObjectMapper().writeValueAsString(request);
        kafkaTemplate.send(AppConstants.AUTH_REQ_TOPIC, messageJson);
        logger.info("Send Kafka topic : {}", AppConstants.AUTH_REQ_TOPIC);
        logger.info("Send Kafka message : {}", request);
    }
}
