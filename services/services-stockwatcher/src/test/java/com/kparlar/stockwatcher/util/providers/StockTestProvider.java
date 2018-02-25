package com.kparlar.stockwatcher.util.providers;

import com.kparlar.stockwatcher.model.entity.Stock;
import com.kparlar.stockwatcher.util.StockWatcherTestConstants;
import com.kparlar.stockwatcher.util.builder.entity.StockBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockTestProvider {

    public Stock getStock(){
        return StockBuilder.stockBuilder().withLastUpdate(new Date()).withCurrentPrice(StockWatcherTestConstants.CURRENT_PRICE_FIRST).withName(StockWatcherTestConstants.STOCK_NAME).withId(StockWatcherTestConstants.STOCK_ID).buildStock();
    }
    public Stock getStock(Date date){
        return StockBuilder.stockBuilder().withLastUpdate(date).withCurrentPrice(StockWatcherTestConstants.CURRENT_PRICE_FIRST).withName(StockWatcherTestConstants.STOCK_NAME).withId(StockWatcherTestConstants.STOCK_ID).buildStock();
    }

    public List<Stock> getOneListStock(){
        Stock tempStock = getStock();
        List<Stock> stocks = new ArrayList<>();
        stocks.add(tempStock);
        return stocks;
    }
    public List<Stock> getOneListStock(Date date){
        Stock tempStock = getStock(date);
        List<Stock> stocks = new ArrayList<>();
        stocks.add(tempStock);
        return stocks;
    }
}
