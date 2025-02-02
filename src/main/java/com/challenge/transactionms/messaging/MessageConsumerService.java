package com.challenge.transactionms.messaging;

import com.challenge.transactionms.model.AuthenticationResponse;
import com.challenge.transactionms.model.TransactionResponseDTO;
import com.challenge.transactionms.utils.AppConstants;
import com.challenge.transactionms.utils.ResponseStore;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumerService.class);
    private final ResponseStore responseStore;

    @KafkaListener(topics = AppConstants.AUTH_RES_TOPIC, groupId = AppConstants.GROUP_ID_CONFIG)
    public void listen(AuthenticationResponse response) {
        TransactionResponseDTO responseDTO = new TransactionResponseDTO(response.getPayload());
        logger.info("Message consumed topic: {}", AppConstants.AUTH_RES_TOPIC);
        logger.info("Message consumed message: {}", response);

        responseStore.completeRequest(response.getRequestId(), new ResponseEntity<>(responseDTO, HttpStatus.OK));
    }
}
