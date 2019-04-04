package com.kparlar.stockwatcher.controller;


import com.kparlar.stockwatcher.exception.ExceptionMessage;
import com.kparlar.stockwatcher.exception.StockWatcherBadRequestException;
import com.kparlar.stockwatcher.exception.StockWatcherException;
import com.kparlar.stockwatcher.util.StockWatcherTestConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import static org.junit.Assert.assertEquals;

public class GlobalControllerExceptionHandlerTest {
    @InjectMocks
    private GlobalControllerExceptionHandler globalControllerExceptionHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleExceptionGivenExceptionThenErrorStatus() {
        ResponseEntity<ExceptionMessage> response =
                globalControllerExceptionHandler.handleException(new Exception(
                        StockWatcherTestConstants.EXCEPTION_TEXT));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void handleRestClientExceptionGivenExceptionThenErrorStatus() {
        ResponseEntity<ExceptionMessage> response =
                globalControllerExceptionHandler.handleRestClientException(new RestClientException(
                        StockWatcherTestConstants.EXCEPTION_TEXT));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void handleStockWatcherExceptionGivenExceptionThenErrorStatus() {
        ResponseEntity<ExceptionMessage> response =
                globalControllerExceptionHandler.handleStockWatcherException(new StockWatcherException(
                        StockWatcherTestConstants.EXCEPTION_TEXT, StockWatcherTestConstants.EXCEPTION_CODE, false));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void handleStockWatcherExceptionGivenStockWatcherBadRequestExceptionThenBadRequestError() {
        ResponseEntity<ExceptionMessage> response =
                globalControllerExceptionHandler.handleStockWatcherException(new StockWatcherBadRequestException(
                        StockWatcherTestConstants.EXCEPTION_TEXT, StockWatcherTestConstants.EXCEPTION_CODE, false));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }



}
