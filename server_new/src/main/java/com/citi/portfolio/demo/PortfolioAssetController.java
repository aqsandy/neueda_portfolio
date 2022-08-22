package com.citi.portfolio.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(path = "/add")
    ResponseEntity<String> add_asset(@RequestBody(required=true) int pid, @RequestBody(required=true) int aid,
                                                  @RequestBody(required=true) float buy_price, @RequestBody(required=true) int shares){
        Map<String, Object> responseBody = new HashMap<String, Object>();
        PortfolioAsset asset = new PortfolioAsset();
        try {
            asset.setPid(pid);
            asset.setAid(aid);
            asset.setBuy_price(buy_price);
            asset.setShares(shares);
            float total_cost = buy_price * shares;
            asset.setTotal_cost(total_cost);
            portfolioAssetRepository.save(asset);
        }catch(Exception e) {
            responseBody.put("status","BAD REQUEST");
            responseBody.put("error",e.toString());
            return ResponseEntity.badRequest().body(new JSONObject(responseBody).toString());
        }
        responseBody.put("status","OK");
        String responseString = (new JSONObject(responseBody).toString());
        return ResponseEntity.accepted().body(responseString);
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<PortfolioAsset> getAllAssets() {
        return portfolioAssetRepository.findAll();
    }
    @DeleteMapping(path = "/delete")
    ResponseEntity<String> delete_asset(@RequestBody(required=true) int product_id){
        Map<String, Object> responseBody = new HashMap<String, Object>();
        PortfolioAsset asset = new PortfolioAsset();
        asset.setProduct_id(product_id);
        try{
            portfolioAssetRepository.deleteById(product_id);
        } catch(Exception e){
            responseBody.put("status","BAD REQUEST");
            responseBody.put("error",e.toString());
            return ResponseEntity.badRequest().body(new JSONObject(responseBody).toString());
        }
        responseBody.put("status","OK");
        String responseString = (new JSONObject(responseBody).toString());
        return ResponseEntity.accepted().body(responseString);
    }
}
