package com.kparlar.stockwatcher.controller.app;

import com.kparlar.stockwatcher.exception.StockWatcherBadRequestException;
import com.kparlar.stockwatcher.exception.StockWatcherException;
import com.kparlar.stockwatcher.exception.StockWatcherNotFoundException;
import com.kparlar.stockwatcher.model.dto.StockDto;
import com.kparlar.stockwatcher.services.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/app/stock-watcher/v1/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ApiOperation(value = "Get all stocks", notes = "If any internal error occured, GlobalControllerException handler return 500 error with unique id")
    @ApiResponses(value =
            {@ApiResponse(code = 200, message = "Successfully get all stocks"),
                    @ApiResponse(code = 500, message = "Internal Server Error, thrown by GlobalControllerException") })
    public ResponseEntity<List<StockDto>> getAllStocks(){
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Get stock with given id", notes = "If there is no data than return 404 HttpNotFound Exception. " +
            "Also if any internal error occured, GlobalControllerException handler return 500 error with unique id")
    @ApiResponses(value =
            {@ApiResponse(code = 200, message = "Successfully get stock with given id"),
                    @ApiResponse(code = 404, message = "Not Found Exception thrown if there is no data for given id"),
                    @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<StockDto> getStock(@PathVariable(value = "id") Long id) throws StockWatcherNotFoundException {
        return new ResponseEntity<>(stockService.getStock(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create stock", notes = "If any internal error occured, GlobalControllerException handler return 500 error with unique id")
    @ApiResponses(value =
            {@ApiResponse(code = 200, message = "Successfully created stock."),
                    @ApiResponse(code = 400, message = "If data is not valid to persist, this error code returns with explanation"),
                    @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<StockDto> createStock(@RequestBody(required = true)StockDto stockDto) throws StockWatcherBadRequestException {
        return new ResponseEntity<>(stockService.createStock(stockDto), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update stock price", notes = "If any internal error occured, GlobalControllerException handler return 500 error with unique id")
    @ApiResponses(value =
            {@ApiResponse(code = 200, message = "Successfully stock is updated."),
                    @ApiResponse(code = 400, message = "Not Valid Exception thrown if current price value is not valid"),
                    @ApiResponse(code = 404, message = "Not Found Exception thrown if there is no data for given id"),
                    @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<StockDto> updateStockPrice(@PathVariable(value = "id") Long id, @RequestBody(required = true)StockDto stockDto) throws StockWatcherException {
        return new ResponseEntity<>(stockService.updateStockPrice(id, stockDto), HttpStatus.OK);
    }
}
