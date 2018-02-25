package com.kparlar.stockwatcher.util;

public final class MessageCodeConstants {
    public static final String REST_CLIENT_EXCEPTION_MESSAGE = "org.springframework.web.client.RestClientException: Code: %s: id %s";
    public static final String REST_CLIENT_EXCEPTION_CODE = "EX01";

    public static final String NOT_FOUND_EXCEPTION_MESSAGE = "Data not found. id :%s";
    public static final String NOT_FOUND_EXCEPTION_CODE = "EX02";

    public static final String DATA_INSERTED_BEFORE_EXCEPTION_MESSAGE = "Data inserted before. id :%s";
    public static final String DATA_INSERTED_BEFORE_EXCEPTION_CODE = "EX03";

    private MessageCodeConstants() {
        throw new IllegalAccessError("Utility class");
    }
}
