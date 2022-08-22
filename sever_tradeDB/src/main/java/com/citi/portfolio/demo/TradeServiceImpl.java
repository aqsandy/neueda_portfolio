package com.citi.portfolio.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService{

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public Iterable<TradeOrder> getTrades() {
        return tradeRepository.findAll();
    }

    @Override
    public TradeOrder getTradeOrderByID(int id) {
        Optional<TradeOrder> tradeOptional = tradeRepository.findById(id);
        if (tradeOptional.isPresent()) {
            return tradeOptional.get();
        }
        else return null;
    }

    @Override
    public TradeOrder getTradeOrderByTicker(String order_ticker) {
        Optional<TradeOrder> tradeOptional = Optional.ofNullable(tradeRepository.findByOrderTicker(order_ticker));
        if (tradeOptional.isPresent()) {
            return tradeOptional.get();
        }
        else return null;
    }

    @Override
    public void deleteTradeOrder(int id) {

    }

    @Override
    public void deleteTradeOrder(TradeOrder tradeOrder) {

    }

    @Override
    public void addTradeOrderByAID(int assetid) {
    }

    @Override
    public void addTradeOrder(TradeOrder tradeOrder) {
        tradeRepository.save(tradeOrder);
    }
}
