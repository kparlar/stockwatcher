package com.kparlar.stockwatcher.util.builder.entity;

import com.kparlar.stockwatcher.model.entity.Stock;

import java.util.Date;

public class StockBuilder {

    private String uuid;
    private Long id;
    private String name;
    private Double currentPrice;
    private Date lastUpdate;


    public static StockBuilder stockBuilder(){
        return new StockBuilder();
    }

    public StockBuilder withUuid(String uuid){
        this.uuid = uuid;
        return this;
    }

    public StockBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public StockBuilder withName(String name){
        this.name = name;
        return this;
    }

    public StockBuilder withCurrentPrice(Double currentPrice){
        this.currentPrice = currentPrice;
        return this;
    }
    public StockBuilder withLastUpdate(Date lastUpdate){
        this.lastUpdate = lastUpdate;
        return this;
    }

    public Stock buildStock(){
        Stock stock = new Stock();
        stock.setCurrentPrice(this.currentPrice);
        stock.setId(this.id);
        stock.setLastUpdate(this.lastUpdate);
        stock.setName(this.name);
        stock.setUuid(this.uuid);
        return stock;
    }


}
