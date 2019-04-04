package com.kparlar.stockwatcher.exception;

import com.kparlar.stockwatcher.util.StockWatcherTestConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExceptionMessageTest {


    private ExceptionMessage exceptionMessage;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.exceptionMessage = new ExceptionMessage();
    }

    @Test
    public void toStringGivenErrorListThenToSting(){
        List<String> errors = new ArrayList<>();
        errors.add(StockWatcherTestConstants.EXCEPTION_TEXT);
        exceptionMessage.setErrors(errors);
        String result = exceptionMessage.toString();
        String expectedResult = "ErrorMessage [errors=" + String.join(",", errors) + "]";
        assertEquals(expectedResult, result);
    }
}
