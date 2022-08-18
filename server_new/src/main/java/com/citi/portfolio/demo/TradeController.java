package com.citi.portfolio.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/trade")
public class TradeController {
     @Autowired
     private AssetRepository assetRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Asset> getAllAssets() {
        // This returns a JSON or XML with the assets
        return assetRepository.findAll();
    }

}
