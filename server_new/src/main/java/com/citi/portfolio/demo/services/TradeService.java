package com.citi.portfolio.demo.services;

import com.citi.portfolio.demo.entities.TradeOrder;

public interface TradeService {
    Iterable<TradeOrder> getTrades();

    TradeOrder getTradeOrderByID(int id);

    Iterable<TradeOrder> getTradeOrdersByTicker(String order_ticker);

    TradeOrder buyTradeOrderBySymbol(String symbol, int num_shares);

    TradeOrder sellTradeOrderBySymbol(String symbol, int num_shares);
}
