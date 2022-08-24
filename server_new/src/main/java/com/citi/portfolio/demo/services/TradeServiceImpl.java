package com.citi.portfolio.demo.services;

import com.citi.portfolio.demo.entities.Asset;
import com.citi.portfolio.demo.entities.TradeOrder;
import com.citi.portfolio.demo.repos.AssetRepository;
import com.citi.portfolio.demo.repos.TradeRepository;
import com.citi.portfolio.demo.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private AssetRepository assetRepository;

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
    public Iterable<TradeOrder> getTradeOrdersByTicker(String order_ticker) {
        return tradeRepository.findAllByOrderTicker(order_ticker);
    }


    @Override
    public TradeOrder buyTradeOrderBySymbol(String symbol, int num_shares) {
        Optional<Asset> assetToAdd = Optional.ofNullable(assetRepository.findBySymbol(symbol));

        TradeOrder orderToAdd = new TradeOrder();

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = new Date();

        orderToAdd.setOrderDate(formatter.format(date));
        orderToAdd.setNumShares(num_shares);
        orderToAdd.setOrderType(assetToAdd.get().getType());
        orderToAdd.setOrderTicker(symbol);
        orderToAdd.setNetAmount(num_shares * assetToAdd.get().getCurrent_price());
        orderToAdd.setStatus_code(0);
        if(assetToAdd.isPresent()) {
            orderToAdd.setOrderStatus("IN PROGRESS");
        }
        else {
            orderToAdd.setOrderStatus("NOT COMPLETED");
        }
        orderToAdd.setPurchaseType("BUY");
        tradeRepository.save(orderToAdd);

        return orderToAdd;
    }

    @Override
    public TradeOrder sellTradeOrderBySymbol(String symbol, int num_shares) {
        Optional<Asset> assetToAdd = Optional.ofNullable(assetRepository.findBySymbol(symbol));

        TradeOrder orderToAdd = new TradeOrder();

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = new Date();

        orderToAdd.setOrderDate(formatter.format(date));
        orderToAdd.setNumShares(num_shares);
        orderToAdd.setOrderType(assetToAdd.get().getType());
        orderToAdd.setOrderTicker(symbol);
        orderToAdd.setNetAmount(num_shares * assetToAdd.get().getCurrent_price());
        orderToAdd.setStatus_code(0);
        if(assetToAdd.isPresent()) {
            orderToAdd.setOrderStatus("IN PROGRESS");
        }
        else {
            orderToAdd.setOrderStatus("NOT COMPLETED");
        }
        orderToAdd.setPurchaseType("SELL");
        tradeRepository.save(orderToAdd);

        return orderToAdd;
    }

    @Override
    public void updateTrades(Iterable<TradeOrder> trades) {
        for(TradeOrder temp: trades)
        {
            if(temp.getStatus_code() == 3) {
                temp.setOrderStatus("FAILED");
                tradeRepository.save(temp);
            }
            else if(temp.getStatus_code() == 2)
            {
                temp.setOrderStatus("FAILED");
                tradeRepository.save(temp);
            }
        }
    }
}
