package com.kparlar.stockwatcher.model.entity;


import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SW_STOCK",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})}, indexes = {@Index(
        name = "SW_STOCK_TNR_IDX", columnList = "ID", unique = true)})
@DiscriminatorValue(value = "PF_PROFILE")
public class Stock extends StockWatcherObject{

    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 4000)
    private String name;

    @Column(name = "CURRENT_PRICE")
    private Double currentPrice;

    @LastModifiedDate
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
