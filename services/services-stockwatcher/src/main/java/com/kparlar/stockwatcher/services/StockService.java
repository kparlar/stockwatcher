package com.kparlar.stockwatcher.services;

import com.kparlar.stockwatcher.exception.StockWatcherBadRequestException;
import com.kparlar.stockwatcher.exception.StockWatcherException;
import com.kparlar.stockwatcher.exception.StockWatcherNotFoundException;
import com.kparlar.stockwatcher.model.dto.StockDto;
import com.kparlar.stockwatcher.model.entity.Stock;
import com.kparlar.stockwatcher.repository.StockRepository;
import com.kparlar.stockwatcher.util.MessageCodeConstants;
import com.kparlar.stockwatcher.util.builder.entity.StockBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }


    public List<StockDto> getAllStocks(){
        List<Stock> stocks = stockRepository.getAllByOrderByIdAsc();
        return stocks.stream().map(stock -> new StockDto(stock.getId(), stock.getName(), stock.getCurrentPrice(), stock.getLastUpdate())).collect(Collectors.toList());
    }

    public StockDto getStock(Long id) throws StockWatcherNotFoundException {
        Stock stock = findStockById(id);
        return new StockDto(stock.getId(), stock.getName(), stock.getCurrentPrice(), stock.getLastUpdate());
    }

    public StockDto createStock(StockDto stockDto) throws StockWatcherBadRequestException {
        if(checkStockIdInsertedBefore(stockDto.getId())){
            String errorMessage = String.format(MessageCodeConstants.DATA_INSERTED_BEFORE_EXCEPTION_MESSAGE, stockDto.getId());
            throw new StockWatcherBadRequestException(errorMessage, MessageCodeConstants.DATA_INSERTED_BEFORE_EXCEPTION_CODE, true);
        }

        Stock stock = StockBuilder.stockBuilder()
                .withId(stockDto.getId())
                .withName(stockDto.getName())
                .withCurrentPrice(stockDto.getCurrentPrice())
                .withLastUpdate(new Date()).buildStock();
        stock = stockRepository.save(stock);
        return new StockDto(stock.getId(), stock.getName(), stock.getCurrentPrice(), stock.getLastUpdate());
    }

    private boolean checkStockIdInsertedBefore(Long id) {
        Stock stock = stockRepository.findById(id);
        if(stock !=null)
            return true;
        else return false;
    }

    public StockDto updateStockPrice(Long id, StockDto stockDto) throws StockWatcherException{
        if(stockDto.getCurrentPrice() == null){
            throw new StockWatcherBadRequestException(MessageCodeConstants.NOT_VALID_CURRENT_PRICE_VALUE_EXCEPTION_MESSAGE, MessageCodeConstants.NOT_VALID_CURRENT_PRICE_VALUE_EXCEPTION_CODE, true);
        }
        Stock stock = findStockById(id);
        stock.setCurrentPrice(stockDto.getCurrentPrice());
        stockRepository.save(stock);
        return new StockDto(stock.getId(), stock.getName(), stock.getCurrentPrice(), stock.getLastUpdate());
    }
    private Stock findStockById(Long id) throws StockWatcherNotFoundException {
        Stock stock = stockRepository.findById(id);
        if(stock == null) {
            String errorMessage = String.format(MessageCodeConstants.NOT_FOUND_EXCEPTION_MESSAGE, id);
            throw new StockWatcherNotFoundException(errorMessage, MessageCodeConstants.NOT_FOUND_EXCEPTION_CODE, true);
        }
        return stock;
    }
}
