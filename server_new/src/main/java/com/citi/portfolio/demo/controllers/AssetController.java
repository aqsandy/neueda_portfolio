package com.citi.portfolio.demo.controllers;

import com.citi.portfolio.demo.services.AssetService;
import com.citi.portfolio.demo.ResponseHandler;
import com.citi.portfolio.demo.entities.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/asset")
@CrossOrigin
public class AssetController {
     @Autowired
     private AssetService assetService;

    @GetMapping(path="/all")
    public ResponseEntity<Object> getAllAssets() {
        // This returns a JSON or XML with the assets
        try {
            Iterable<Asset> result = assetService.getAssets();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getAssetById(@PathVariable("id") int id) {
        try {
            Asset result = assetService.getAssetById(id);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/stock/{symbol}")
    public ResponseEntity<Object> getAssetBySymbol(@PathVariable("symbol") String symbol)
    {
        try {
            Asset result = assetService.getAssetBySymbol(symbol);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
