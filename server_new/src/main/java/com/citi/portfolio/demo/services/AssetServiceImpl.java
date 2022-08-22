package com.citi.portfolio.demo.services;

import com.citi.portfolio.demo.entities.Asset;
import com.citi.portfolio.demo.repos.AssetRepository;
import com.citi.portfolio.demo.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;
    @Override
    public Iterable<Asset> getAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(int id) {
        Optional<Asset> assetOptional = assetRepository.findById(id);
        if (assetOptional.isPresent()) {
            return assetOptional.get();
        }
        else return null;
    }

    @Override
    public Asset getAssetBySymbol(String sym) {
        Optional<Asset> assetOptional = Optional.ofNullable(assetRepository.findBySymbol(sym));
        if (assetOptional.isPresent()) {
            return assetOptional.get();
        }
        else return null;
    }

}
