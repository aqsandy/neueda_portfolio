package com.citi.portfolio.demo.repos;

import org.springframework.data.repository.CrudRepository;
import com.citi.portfolio.demo.entities.PortfolioAsset;

import java.util.Optional;

public interface PortfolioAssetRepository extends CrudRepository<PortfolioAsset, Integer>  {
        public PortfolioAsset findByPidAndAid(int pid, int aid);
}
