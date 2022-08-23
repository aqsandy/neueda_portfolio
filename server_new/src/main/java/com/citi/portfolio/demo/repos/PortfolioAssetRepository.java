package com.citi.portfolio.demo.repos;

import org.springframework.data.repository.CrudRepository;
import com.citi.portfolio.demo.entities.PortfolioAsset;
public interface PortfolioAssetRepository extends CrudRepository<PortfolioAsset, Integer>  {

}
