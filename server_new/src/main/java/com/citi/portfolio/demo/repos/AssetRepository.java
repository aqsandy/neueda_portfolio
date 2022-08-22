package com.citi.portfolio.demo.repos;

import com.citi.portfolio.demo.entities.Asset;
import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends CrudRepository<Asset,Integer>{

    public Asset findBySymbol(String symbol);
}
