package com.citi.portfolio.demo;

public interface AssetService {

    Iterable<Asset> getAssets();

    Asset getAssetById(int id);

    Asset getAssetBySymbol(String sym);

}
