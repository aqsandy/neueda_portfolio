package com.citi.portfolio.demo.repos;

import com.citi.portfolio.demo.entities.TradeOrder;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<TradeOrder,Integer>{

    public Iterable<TradeOrder> findAllByOrderTicker(String ticker);

}
