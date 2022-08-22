package com.citi.portfolio.demo.services;

import com.citi.portfolio.demo.entities.Asset;

public interface AssetService {

    Iterable<Asset> getAssets();

    Asset getAssetById(int id);

    Asset getAssetBySymbol(String sym);

}
