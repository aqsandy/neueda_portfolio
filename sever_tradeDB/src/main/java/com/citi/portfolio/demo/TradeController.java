package com.citi.portfolio.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/tradeOrder")
public class TradeController {
     @Autowired
     private TradeRepository tradeRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<TradeOrder> getAllAssets() {
        // This returns a JSON or XML with all the trades
        return tradeRepository.findAll();
    }

}
