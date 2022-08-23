package com.citi.portfolio.demo.controllers;
import com.citi.portfolio.demo.repos.PortfolioAssetRepository;
import com.citi.portfolio.demo.ResponseHandler;
import com.citi.portfolio.demo.dto.PortofolioDelete_DTO;
import com.citi.portfolio.demo.entities.PortfolioAsset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Map<String, Object> responseBody = new HashMap<String, Object>() ;
        try {
            portfolioAssetRepository.save(asset);
        }catch(Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
        return ResponseHandler.generateResponse("Added the asset in portfolio successfully.", HttpStatus.OK, null);
    }
    @GetMapping(path="/all")
    ResponseEntity<Object>  getAllAssets() {
        Iterable<PortfolioAsset> result = portfolioAssetRepository.findAll();
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
    }
    @DeleteMapping(path = "/delete")
    ResponseEntity<Object> delete_asset(@RequestBody(required=true) PortofolioDelete_DTO delete_item){
        try{
            portfolioAssetRepository.deleteById(delete_item.getPid());
        } catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
        return ResponseHandler.generateResponse("Delete the asset in portfolio successfully.", HttpStatus.OK, null);
    }
}
