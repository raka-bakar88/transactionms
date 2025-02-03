package com.challenge.transactionms.utils;


public final class AppConstants {
    private AppConstants() {
    }
    public static final int TIME_OUT = 5;
    //kafka
    public static final String BOOTSTRAP_SERVERS_CONFIG = "localhost:9092";
    public static final String GROUP_ID_CONFIG = "transaction-group-id";
    public static final String AUTH_REQ_TOPIC = "authentication_request_topic";
    public static final String AUTH_RES_TOPIC = "authentication_response_topic";

}
