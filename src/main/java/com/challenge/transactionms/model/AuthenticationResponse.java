package com.challenge.transactionms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private String requestId;
    private RequestType type;
    private String payload;
}
