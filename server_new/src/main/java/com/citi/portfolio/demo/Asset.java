package com.citi.portfolio.demo;

import javax.persistence.*;

@Entity @Table(name="Asset")

public class Asset {

    @Column(name="symbol")
    private String Symbol;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="aid")
    private Integer AssetID;
    @Column(name="name")
    private String Name;
    @Column(name="current_price")
    private int CurrentPrice;
    @Column(name="market_value")
    private long MarketValue;
    @Column(name="yield_percent")
    private float Yield;
    @Column(name="high")
    private int High;
    @Column(name="low")
    private int Low;
    //Methods
    @Column(name="type")
    private String AssetType;

    public Asset(String symbol, Integer assetID, String name, int currentPrice, long marketValue, float yield, int high, int low, String assetType) {
        Symbol = symbol;
        AssetID = assetID;
        Name = name;
        CurrentPrice = currentPrice;
        MarketValue = marketValue;
        Yield = yield;
        High = high;
        Low = low;
        AssetType = assetType;
    }


    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public Integer getAssetID() {
        return AssetID;
    }

    public void setAssetID(Integer assetID) {
        AssetID = assetID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getYield() {
        return Yield;
    }

    public void setYield(float yield) {
        Yield = yield;
    }

    public int getHigh() {
        return High;
    }

    public void setHigh(int high) {
        High = high;
    }

    public int getLow() {
        return Low;
    }

    public void setLow(int low) {
        Low = low;
    }

    public String getAssetType() {
        return AssetType;
    }

    public void setAssetType(String assetType) {
        AssetType = assetType;
    }
}
