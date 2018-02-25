package com.kparlar.stockwatcher.util.providers;

import com.kparlar.stockwatcher.model.dto.StockDto;
import com.kparlar.stockwatcher.util.StockWatcherTestConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockDtoTestProvider {

    public StockDto getStockDto(){
        return new StockDto(StockWatcherTestConstants.STOCK_ID, StockWatcherTestConstants.STOCK_NAME, StockWatcherTestConstants.CURRENT_PRICE_FIRST, new Date());
    }
    public StockDto getStockDto(Date date){
        return new StockDto(StockWatcherTestConstants.STOCK_ID, StockWatcherTestConstants.STOCK_NAME, StockWatcherTestConstants.CURRENT_PRICE_FIRST, date);
    }

    public List<StockDto> getOneListStockDto(){
        StockDto tempStockDto = getStockDto();
        List<StockDto> stockDtos = new ArrayList<>();
        stockDtos.add(tempStockDto);
        return stockDtos;
    }
    public List<StockDto> getOneListStockDto(Date date){
        StockDto tempStockDto = getStockDto(date);
        List<StockDto> stockDtos = new ArrayList<>();
        stockDtos.add(tempStockDto);
        return stockDtos;
    }

}
