package com.citi.portfolio.demo;

public interface TradeService {
    Iterable<TradeOrder> getTrades();

    TradeOrder getTradeOrderByID(int id);

    TradeOrder getTradeOrderByTicker(String order_ticker);

    void deleteTradeOrder(int id);

    void deleteTradeOrder(TradeOrder tradeOrder);

    void addTradeOrderByAID(int assetid);

    void addTradeOrder(TradeOrder tradeOrder);
}
