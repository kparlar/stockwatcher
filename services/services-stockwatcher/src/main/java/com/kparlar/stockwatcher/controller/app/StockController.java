package com.kparlar.stockwatcher.controller.app;

import com.kparlar.stockwatcher.model.dto.StockDto;
import com.kparlar.stockwatcher.services.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                    @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<List<StockDto>> getAllStocks(){
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }
}
