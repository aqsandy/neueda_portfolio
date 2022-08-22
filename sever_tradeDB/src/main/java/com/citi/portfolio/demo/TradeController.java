package com.citi.portfolio.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/trade")
public class TradeController {
     @Autowired
     private TradeService tradeService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<TradeOrder> getAllOrders() {
        // This returns a JSON or XML with all the trades
        return tradeService.getTrades();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public TradeOrder getTradeById(@PathVariable("id") int id) {
        if(tradeService.getTradeOrderByID(id) == null)
            throw new TradeNotFoundException();
        else
            return tradeService.getTradeOrderByID(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/order/{ticker}")
    public TradeOrder getAssetBySymbol(@PathVariable("ticker") String ticker)
    {
        if(tradeService.getTradeOrderByTicker(ticker) == null)
            throw new TradeNotFoundException();
        else
            return tradeService.getTradeOrderByTicker(ticker);
    }



}

