package com.conygre.spring.boot.repos;

import com.conygre.spring.boot.entities.Asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

    public Iterable<Asset> findBySymbol(String sym);
}