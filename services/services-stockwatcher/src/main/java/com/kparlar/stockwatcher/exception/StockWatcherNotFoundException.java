package com.kparlar.stockwatcher.exception;

import org.springframework.http.HttpStatus;

public class StockWatcherNotFoundException extends StockWatcherException {
    public StockWatcherNotFoundException(String errorMessage, String errorCode, boolean showMessage) {
        super(errorMessage, errorCode, HttpStatus.NOT_FOUND, showMessage);
    }
}
