package com.citi.portfolio.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/PortfolioAsset")
@CrossOrigin
public class PortfolioAssetController {
    @Autowired
    private PortfolioAssetRepository portfolioAssetRepository;
    @PostMapping(path = "/add")
    ResponseEntity<Object> add_asset(@RequestBody PortfolioAsset asset){
        Map<String, Object> responseBody = new HashMap<String, Object>();
        try {
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
    ResponseEntity<Object>  getAllAssets() {
        Iterable<PortfolioAsset> result = portfolioAssetRepository.findAll();
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
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
