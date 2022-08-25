package com.citi.portfolio.demo.repos;

import com.citi.portfolio.demo.entities.Asset;
import com.citi.portfolio.demo.entities.joke;
import org.springframework.data.repository.CrudRepository;

public interface JokeRepository extends CrudRepository<joke,Integer>{

}