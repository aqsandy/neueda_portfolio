package com.citi.portfolio.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface AssetRepository extends CrudRepository<Asset,Integer>{

    public Asset findBySymbol(String symbol);
}
