package com.kparlar.stockwatcher.services;

import com.kparlar.stockwatcher.model.dto.StockDto;
import com.kparlar.stockwatcher.model.entity.Stock;
import com.kparlar.stockwatcher.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }


    public List<StockDto> getAllStocks(){
        List<Stock> stocks = (List<Stock>) stockRepository.findAll();
        return stocks.stream().map(stock -> new StockDto(stock.getId(), stock.getName(), stock.getCurrentPrice(), stock.getLastUpdate())).collect(Collectors.toList());
    }
}
