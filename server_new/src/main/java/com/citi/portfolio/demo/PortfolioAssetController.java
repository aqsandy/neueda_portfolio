package com.citi.portfolio.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path="/PortfolioAsset")
public class PortfolioAssetController {
    @Autowired
    private PortfolioAssetRepository portfolioAssetRepository;

    @PostMapping(path = "/buy")
    public @ResponseBody String buy_asset(@RequestParam Integer pid, @RequestParam Integer aid,
                                         @RequestParam Float buy_price, @RequestParam Integer shares){
        Map<String, Object> responseBody = new HashMap<String, Object>();
        PortfolioAsset asset = new PortfolioAsset();
        asset.setPid(pid);
        asset.setAid(aid);
        asset.setBuy_price(buy_price);
        asset.setShares(shares);
        Float total_cost = buy_price * shares;
        asset.setTotal_cost(total_cost);
        responseBody.put("status","OK");
        String responseString = (new JSONObject(responseBody)).toString(2);
        return responseString;
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<PortfolioAsset> getAllAssets() {
        // This returns a JSON or XML with the users
        return portfolioAssetRepository.findAll();
    }
}
