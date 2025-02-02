package com.challenge.transactionms.model;

public enum RequestType {
    AUTHENTICATION_REQUEST("authentication_request"),
    AUTHENTICATION_RESPONSE("authentication_response");

    private final String type;

    RequestType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
