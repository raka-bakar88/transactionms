package com.challenge.transactionms.controller;

import com.challenge.transactionms.messaging.MessageProducerService;
import com.challenge.transactionms.model.AuthenticationRequest;
import com.challenge.transactionms.model.RequestType;
import com.challenge.transactionms.model.TransactionResponseDTO;
import com.challenge.transactionms.model.request.TransactionRequestDTO;
import com.challenge.transactionms.utils.ResponseStore;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private ResponseStore responseStore;

    @PostMapping("/authorize")
    public CompletableFuture<ResponseEntity<TransactionResponseDTO>> authorize(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        logger.info("POST request /authorize with requestBody: {}", transactionRequestDTO);

        String requestId = UUID.randomUUID().toString();
        AuthenticationRequest request = new AuthenticationRequest(requestId,
                RequestType.AUTHENTICATION_REQUEST,
                transactionRequestDTO.getDriverIdentifierDTO().getId());

        CompletableFuture<ResponseEntity<TransactionResponseDTO>> futureResponse = new CompletableFuture<>();
        responseStore.storePendingRequest(requestId, futureResponse);

        messageProducerService.authorize(request);

        return futureResponse;
    }
}
