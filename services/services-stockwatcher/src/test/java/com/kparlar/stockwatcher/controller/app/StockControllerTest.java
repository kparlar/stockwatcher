package com.kparlar.stockwatcher.controller.app;

import com.kparlar.stockwatcher.exception.StockWatcherBadRequestException;
import com.kparlar.stockwatcher.exception.StockWatcherException;
import com.kparlar.stockwatcher.exception.StockWatcherNotFoundException;
import com.kparlar.stockwatcher.model.dto.StockDto;
import com.kparlar.stockwatcher.services.StockService;
import com.kparlar.stockwatcher.util.StockWatcherTestConstants;
import com.kparlar.stockwatcher.util.providers.StockDtoTestProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

public class StockControllerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private StockService stockService;

    private StockController stockController;
    private StockDtoTestProvider stockDtoTestProvider;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        stockController = new StockController(stockService);
        stockDtoTestProvider = new StockDtoTestProvider();
    }

    @Test
    public void getAllStocksGivenNothingWhenGettingAllStocksThenReturnStockDtoList(){
        List<StockDto> expectedResult = stockDtoTestProvider.getOneListStockDto();
        Mockito.when(stockService.getAllStocks()).thenReturn(expectedResult);
        ResponseEntity<List<StockDto>> response = stockController.getAllStocks();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void getStockGivenValidIdWhenSuccessfullyRetrievedStockThenReturnStock() throws StockWatcherNotFoundException {
        StockDto expectedResult  = stockDtoTestProvider.getStockDto();
        Mockito.when(stockService.getStock(StockWatcherTestConstants.STOCK_ID)).thenReturn(expectedResult);
        ResponseEntity<StockDto> response = stockController.getStock(StockWatcherTestConstants.STOCK_ID);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void getStockGivenNotValidIdWhenDataNotFoundThenThrowDataNotFoundException() throws StockWatcherNotFoundException {
        doThrow(StockWatcherNotFoundException.class).when(stockService).getStock(StockWatcherTestConstants.STOCK_ID);
        this.thrown.expect(StockWatcherNotFoundException.class);
        stockController.getStock(StockWatcherTestConstants.STOCK_ID);
    }

    @Test
    public void createStockGivenValidDataWhenCreatingStockThenStockDto() throws StockWatcherBadRequestException {
        StockDto expectedResult = stockDtoTestProvider.getStockDto();
        StockDto inputParam = new StockDto();
        Mockito.when(stockService.createStock(inputParam)).thenReturn(expectedResult);
        ResponseEntity<StockDto> response = stockController.createStock(inputParam);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(expectedResult, response.getBody());
    }
    @Test
    public void createStockGivenNotValidDataWhenDataNotValidThenThrowBadRequestException() throws StockWatcherBadRequestException {
        StockDto inputParam = new StockDto();
        doThrow(StockWatcherBadRequestException.class).when(stockService).createStock(inputParam);
        this.thrown.expect(StockWatcherBadRequestException.class);
        stockController.createStock(inputParam);
    }

    @Test
    public void updateStockPriceGivenValidDataWhenUpdatingStockThenStockDto() throws StockWatcherException {
        StockDto expectedResult = stockDtoTestProvider.getStockDto();
        StockDto inputParam = new StockDto();
        Mockito.when(stockService.updateStockPrice(StockWatcherTestConstants.STOCK_ID, inputParam)).thenReturn(expectedResult);
        ResponseEntity<StockDto> response = stockController.updateStockPrice(StockWatcherTestConstants.STOCK_ID, inputParam);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(expectedResult, response.getBody());
    }
    @Test
    public void updateStockPriceGivenNotValidDataWhenDataNotValidThenThrowNotFoundException() throws StockWatcherException {
        StockDto inputParam = new StockDto();
        doThrow(StockWatcherNotFoundException.class).when(stockService).updateStockPrice(StockWatcherTestConstants.STOCK_ID, inputParam);
        this.thrown.expect(StockWatcherNotFoundException.class);
        stockController.updateStockPrice(StockWatcherTestConstants.STOCK_ID, inputParam);
    }
    @Test
    public void updateStockPriceGivenNotValidDataWhenDataNotValidThenThrowBadRequestException() throws StockWatcherException {
        StockDto inputParam = new StockDto();
        doThrow(StockWatcherBadRequestException.class).when(stockService).updateStockPrice(StockWatcherTestConstants.STOCK_ID, inputParam);
        this.thrown.expect(StockWatcherBadRequestException.class);
        stockController.updateStockPrice(StockWatcherTestConstants.STOCK_ID, inputParam);
    }

}
