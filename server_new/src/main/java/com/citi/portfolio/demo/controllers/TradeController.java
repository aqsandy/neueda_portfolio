package com.citi.portfolio.demo.controllers;

import com.citi.portfolio.demo.ResponseHandler;
import com.citi.portfolio.demo.dto.Trade_DTO;
import com.citi.portfolio.demo.services.TradeService;
import com.citi.portfolio.demo.entities.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/trade")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @GetMapping(path = "/all")
    public ResponseEntity<Object> getAllOrders() {
        // This returns a JSON or XML with all the trades
        try {
            Iterable<TradeOrder> result = tradeService.getTrades();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getTradeById(@PathVariable("id") int id) {
        try {
            TradeOrder result = tradeService.getTradeOrderByID(id);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }


    @RequestMapping(method = RequestMethod.GET, value = "/order/{ticker}")
    public ResponseEntity<Object> getTradeBySymbol(@PathVariable("ticker") String ticker) {
        try {
            Iterable<TradeOrder> result = tradeService.getTradeOrdersByTicker(ticker);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/buy")
    public ResponseEntity<Object> buyTradeOrderByTicker(@RequestBody Trade_DTO trade) {
        try {
            TradeOrder result = tradeService.buyTradeOrderBySymbol(trade.getTicker(), trade.getNumShares());
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/sell")
    public ResponseEntity<Object> sellTradeOrderByTicker(@RequestBody Trade_DTO trade) {
        try {
            TradeOrder result = tradeService.sellTradeOrderBySymbol(trade.getTicker(), trade.getNumShares());
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    @RequestMapping(method= RequestMethod.PUT, value = "/update")
        public ResponseEntity<String> updateOrderHistory()
        {
            try {
                Iterable<TradeOrder> trades = tradeService.getTrades();
                tradeService.updateTrades(trades);
                ResponseEntity<String> trades_successfully_updated = new ResponseEntity<>("Trades successfully updated", HttpStatus.OK);
                return trades_successfully_updated;
            }
            catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        }

    }


