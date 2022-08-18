package com.citi.portfolio.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/asset")
@CrossOrigin
public class AssetController {
     @Autowired
     private AssetService assetService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Asset> getAllAssets() {
        // This returns a JSON or XML with the assets
        return assetService.getAssets();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Asset getAssetById(@PathVariable("id") int id) {
        return assetService.getAssetById(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/stock/{symbol}")
    public Asset getAssetBySymbol(@PathVariable("symbol") String symbol)
    {
        return assetService.getAssetBySymbol(symbol);
    }

}
