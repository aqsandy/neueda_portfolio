package com.citi.portfolio.demo.controllers;

import com.citi.portfolio.demo.ResponseHandler;
import com.citi.portfolio.demo.repos.OrderProcessingSimulator;
import com.citi.portfolio.demo.services.TradeService;
import com.citi.portfolio.demo.entities.TradeOrder;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
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

    @Autowired
    private OrderProcessingSimulator op;

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
    public ResponseEntity<Object> buyTradeOrderByTicker(@RequestParam String ticker, @RequestParam int numShares) {
        try {
            TradeOrder result = tradeService.buyTradeOrderBySymbol(ticker, numShares);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/sell")
    public ResponseEntity<Object> sellTradeOrderByTicker(@RequestParam String ticker, @RequestParam int numShares) {
        try {
            TradeOrder result = tradeService.sellTradeOrderBySymbol(ticker, numShares);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(value = "/update")
        public ResponseEntity<String> updateOrderHistory()
        {
            try {
                int stepOne = op.findTradesForProcessing();
                int stepTwo = op.findTradesForFillingOrRejecting();
                ResponseEntity<String> trades_successfully_updated = new ResponseEntity<>("Trades successfully updated", HttpStatus.OK);
                return trades_successfully_updated;
            }
            catch (Exception e){
                return new ResponseEntity<>("Trades didn't update sucesfully", HttpStatus.BAD_REQUEST);

        }

        }
    }


