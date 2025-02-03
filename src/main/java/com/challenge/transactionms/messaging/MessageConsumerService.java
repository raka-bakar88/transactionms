package com.challenge.transactionms.messaging;

import com.challenge.transactionms.model.MessageResponse;
import com.challenge.transactionms.model.TransactionResponseDTO;
import com.challenge.transactionms.utils.AppConstants;
import com.challenge.transactionms.utils.ResponseStore;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void listen(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MessageResponse authenticationResponse = objectMapper.readValue(response, MessageResponse.class);
            TransactionResponseDTO responseDTO = new TransactionResponseDTO(authenticationResponse.getPayload());
            logger.info("Message consumed topic: {}", AppConstants.AUTH_RES_TOPIC);
            logger.info("Message consumed message: {}", response);
            switch (responseDTO.getAuthorizationStatus()) {
                case "ACCEPTED":
                    responseStore.completeRequest(authenticationResponse.getRequestId(), new ResponseEntity<>(responseDTO, HttpStatus.OK));
                case "REJECTED":
                    responseStore.completeRequest(authenticationResponse.getRequestId(), new ResponseEntity<>(responseDTO, HttpStatus.FORBIDDEN));
                case "INVALID":
                    responseStore.completeRequest(authenticationResponse.getRequestId(), new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST));
                case "UNKNOWN":
                    responseStore.completeRequest(authenticationResponse.getRequestId(), new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR));

                default:
                    responseStore.completeRequest(authenticationResponse.getRequestId(), new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
