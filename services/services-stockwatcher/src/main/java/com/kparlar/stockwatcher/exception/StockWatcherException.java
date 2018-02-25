package com.kparlar.stockwatcher.exception;

import org.springframework.http.HttpStatus;

public class StockWatcherException extends Exception {

    private final HttpStatus status;
    private final String errorMessage;
    private final String errorCode;
    private final Exception carriedOverException;
    private final boolean showMessage;

    public StockWatcherException(String errorMessage, String errorCode, boolean showMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.showMessage = showMessage;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;// Default http status when not given
        this.carriedOverException = null;
    }
    public StockWatcherException(String errorMessage, String errorCode, Exception exception,
                                 boolean showMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.showMessage = showMessage;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;// Default http status when not given
        this.carriedOverException = exception;
    }
    public StockWatcherException(String errorMessage, String errorCode, HttpStatus httpStatus, boolean showMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.showMessage = showMessage;
        this.status = httpStatus;// Default http status when not given
        this.carriedOverException = null;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Exception getCarriedOverException() {
        return carriedOverException;
    }

    public boolean isShowMessage() {
        return showMessage;
    }

    @Override
    public String toString() {
        return "PowerFilerException{" + "status=" + status + ", errorMessage='" + errorMessage + '\''
                + ", errorCode='" + errorCode + '\'' + '}';
    }

}
