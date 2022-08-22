package com.citi.portfolio.demo;

import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<TradeOrder,Integer>{

    public TradeOrder findByOrderTicker(String ticker);

}
