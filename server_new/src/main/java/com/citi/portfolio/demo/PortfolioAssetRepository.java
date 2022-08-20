package com.citi.portfolio.demo;

import org.springframework.data.repository.CrudRepository;
import com.citi.portfolio.demo.PortfolioAsset;
public interface PortfolioAssetRepository extends CrudRepository<PortfolioAsset, Integer>  {

}
