// this is a regular JavaBean that instances of will be passed
// back to the web application clients
// the alternative is to pass entity bean references back for the CDs,
// but this would mean a substantial overhead in remote reference calls as all the data
// is accessed by the pages

package com.conygre.spring.boot.entities;
import javax.persistence.*;


import java.io.Serializable;

//Adding caching
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)


@Entity @Table(name="Asset")
public class Asset implements Serializable {

    //Instance variables
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
    @Column(name="Type")
    private enum AssetType {
        STOCK,
        CASH,
        BOND;
    }

    public Integer getAssetID() {
        return this.AssetID;
    }

    public void setAssetID(Integer assetID) {
        this.AssetID = assetID;
    }


    public int getCurrentPrice() {
        return this.CurrentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.CurrentPrice = currentPrice;
    }

    public long getMarketValue() {
        return this.MarketCap;
    }

    public void setMarketValue(long marketCap) {
        this.MarketValue = marketCap;
    }

    public float getYield() {
        return this.Yield;
    }

    public void setYield(float yield) {
        this.Yield = yield;
    }


    public int getHigh() {
        return this.High;
    }

    public void setHigh(int high) {
        this.High = high;
    }

    public int getLow() {
        return this.Low;
    }

    public void setLow(int low) {
        this.Low = low;
    }

    public String getName() {
       return Name;
    }

    public setName(String name)
    {
        this.Name = name;
    }

    public String getSymbol()
    {
        return this.Symbol;
    }

    public setSymbol(String sym)
    {
        this.Symbol = sym;
    }

    public enum getAssetType()
    {
        return this.AssetType;
    }

    public setAssetType()
    {
        return this.AssetType();
    }





    //constructors
    public Asset(){}

    public Asset(int assetID, String name, String symbol, int currentPrice, long marketCap, double yield,
                 enum assetType, int high, int low){
        this.AssetID = assetID;
        this.Name=name;
        this.Symbol = symbol;
        this.CurrentPricePrice = currentPrice;
        this.MarketVolume = marketCap;
        this.Yield = yield;
        this.Type = assetType;
        this.High = high;
        this.Low = low;
    }
}