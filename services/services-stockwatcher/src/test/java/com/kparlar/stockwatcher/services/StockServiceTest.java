package com.kparlar.stockwatcher.services;

import com.kparlar.stockwatcher.exception.StockWatcherBadRequestException;
import com.kparlar.stockwatcher.exception.StockWatcherException;
import com.kparlar.stockwatcher.exception.StockWatcherNotFoundException;
import com.kparlar.stockwatcher.model.dto.StockDto;
import com.kparlar.stockwatcher.model.entity.Stock;
import com.kparlar.stockwatcher.repository.StockRepository;
import com.kparlar.stockwatcher.util.StockWatcherTestConstants;
import com.kparlar.stockwatcher.util.providers.StockDtoTestProvider;
import com.kparlar.stockwatcher.util.providers.StockTestProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StockServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private StockRepository stockRepository;
    private StockService stockService;
    private StockTestProvider stockTestProvider;
    private StockDtoTestProvider stockDtoTestProvider;
    private Date date;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.stockService = new StockService(this.stockRepository);
        this.stockTestProvider = new StockTestProvider();
        this.stockDtoTestProvider = new StockDtoTestProvider();
        this.date = new Date();
    }

    @Test
    public void getAllStocksGivenWhenGettingAllStocksThenStockList(){
        List<StockDto> expectedResult =  this.stockDtoTestProvider.getOneListStockDto(this.date);
        List<Stock> stocks = this.stockTestProvider.getOneListStock(this.date);
        Mockito.when(stockRepository.getAllByOrderByIdAsc()).thenReturn(stocks);
        List<StockDto> response = stockService.getAllStocks();
        assertEquals(expectedResult.get(0).getId(), response.get(0).getId());
        assertEquals(expectedResult.get(0).getCurrentPrice(), response.get(0).getCurrentPrice());
        assertEquals(expectedResult.get(0).getName(), response.get(0).getName());
        assertEquals(expectedResult.get(0).getLastUpdate(), response.get(0).getLastUpdate());
    }

    @Test
    public void getStockGivenValidStockIdWhenGettingStockReturnStockDto() throws StockWatcherNotFoundException {
        Stock stock = this.stockTestProvider.getStock(this.date);
        StockDto expectedResult = this.stockDtoTestProvider.getStockDto(this.date);
        Mockito.when(stockRepository.findById(StockWatcherTestConstants.STOCK_ID)).thenReturn(stock);
        StockDto response =  stockService.getStock(StockWatcherTestConstants.STOCK_ID);
        assertEquals(expectedResult.getLastUpdate(), response.getLastUpdate());
        assertEquals(expectedResult.getName(), response.getName());
        assertEquals(expectedResult.getCurrentPrice(), response.getCurrentPrice());
        assertEquals(expectedResult.getId(), response.getId());
    }
    @Test
    public void getStockGivenNotValidStockIdWhenStockNotFoundThenThrowStockWatcherNotFoundException() throws StockWatcherNotFoundException {
        when(stockRepository.findById(StockWatcherTestConstants.STOCK_ID)).thenReturn(null);
        this.thrown.expect(StockWatcherNotFoundException.class);
        stockService.getStock(StockWatcherTestConstants.STOCK_ID);
    }

    @Test
    public void createStockGivenValidStockDtoWhenNoStockFoundWithGivenIdThenCreatedStockDto() throws StockWatcherBadRequestException {
        StockDto expectedResult = this.stockDtoTestProvider.getStockDto(this.date);
        Stock stock = this.stockTestProvider.getStock(this.date);
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);
        when(stockRepository.findById(StockWatcherTestConstants.STOCK_ID)).thenReturn(null);
        StockDto response = stockService.createStock(expectedResult);
        assertEquals(expectedResult.getLastUpdate(), response.getLastUpdate());
        assertEquals(expectedResult.getName(), response.getName());
        assertEquals(expectedResult.getCurrentPrice(), response.getCurrentPrice());
        assertEquals(expectedResult.getId(), response.getId());
        verify(stockRepository, Mockito.atLeastOnce()).save(any(Stock.class));
    }

    @Test
    public void createStockGivenValidStockDtoWhenStockFoundWithGivenIdThenThrowBadRequestException() throws StockWatcherBadRequestException {
        StockDto stockDto = this.stockDtoTestProvider.getStockDto(this.date);
        when(stockRepository.findById(StockWatcherTestConstants.STOCK_ID)).thenReturn(new Stock());
        this.thrown.expect(StockWatcherBadRequestException.class);
        stockService.createStock(stockDto);
    }

    @Test
    public void updateStockPriceGivenIdAndStockDtoWhenCurrentPriceValueNotValidThenThrowBadRequestException() throws StockWatcherException {
        StockDto stockDto = new StockDto();
        this.thrown.expect(StockWatcherBadRequestException.class);
        stockService.updateStockPrice(StockWatcherTestConstants.STOCK_ID, stockDto);
    }

    @Test
    public void updateStockPriceGivenIdAndStockDtoWhenStockNotFoundThenThrowNotFoundException() throws StockWatcherException {
        StockDto stockDto = this.stockDtoTestProvider.getStockDto(this.date);
        Mockito.when(stockRepository.findById(StockWatcherTestConstants.STOCK_ID)).thenReturn(null);
        this.thrown.expect(StockWatcherNotFoundException.class);
        stockService.updateStockPrice(StockWatcherTestConstants.STOCK_ID, stockDto);
    }

    @Test
    public void updateStockPriceGivenIdAndStockDtoWhenStockFoundThenUpdatedStockDto() throws StockWatcherException {
        StockDto expectedResult = this.stockDtoTestProvider.getStockDto(this.date);
        expectedResult.setCurrentPrice(StockWatcherTestConstants.CURRENT_PRICE_SECOND);
        Stock stock = this.stockTestProvider.getStock(this.date);
        Mockito.when(stockRepository.findById(StockWatcherTestConstants.STOCK_ID)).thenReturn(stock);
        StockDto response = stockService.updateStockPrice(StockWatcherTestConstants.STOCK_ID, expectedResult);
        assertEquals(expectedResult.getCurrentPrice(), response.getCurrentPrice());
        verify(stockRepository, Mockito.atLeastOnce()).save(any(Stock.class));
    }



}
