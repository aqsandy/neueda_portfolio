package com.citi.portfolio.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService{

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
