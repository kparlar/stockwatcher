package com.kparlar.stockwatcher.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kparlar.stockwatcher.model.json.JsonDateSerializer;

import java.util.Date;

public class StockDto {

    private Long id;
    private String name;
    private Double currentPrice;
    private Date lastUpdate;


    public StockDto(){

    }

    public StockDto(Long id, String name, Double currentPrice, Date lastUpdate){
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getLastUpdate() {
        return lastUpdate;
    }

}
