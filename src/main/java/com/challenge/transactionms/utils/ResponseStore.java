package com.challenge.transactionms.utils;

import com.challenge.transactionms.model.TransactionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class acts as temporary storage mechanism that holds pending HTTP requests
 * waiting for a response from Kafka. It is implemented to handles HTTP Response that needs to wait
 * Kafka Asynchronous messaging mechanism
 */
@Component
public class ResponseStore {

    private final ConcurrentHashMap<String, CompletableFuture<ResponseEntity<TransactionResponseDTO>>> pendingRequests = new ConcurrentHashMap<>();

    public void storePendingRequest(String requestId, CompletableFuture<ResponseEntity<TransactionResponseDTO>> future) {
        pendingRequests.put(requestId, future);
    }

    public CompletableFuture<ResponseEntity<TransactionResponseDTO>> getPendingRequest(String requestId) {
        return pendingRequests.get(requestId);
    }

    public void completeRequest(String requestId, ResponseEntity<TransactionResponseDTO> result) {
        CompletableFuture<ResponseEntity<TransactionResponseDTO>> future = pendingRequests.remove(requestId);
        if (future != null) {
            future.complete(result);
        }
    }
}
