package com.kparlar.stockwatcher.exception;

import org.springframework.http.HttpStatus;

public class StockWatcherBadRequestException extends StockWatcherException {
    public StockWatcherBadRequestException(String errorMessage, String errorCode, boolean showMessage) {
        super(errorMessage, errorCode, HttpStatus.BAD_REQUEST, showMessage);
    }
}
