package com.kparlar.stockwatcher.repository;

import com.kparlar.stockwatcher.model.entity.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, String>{

    public Stock findById(Long id);
}
