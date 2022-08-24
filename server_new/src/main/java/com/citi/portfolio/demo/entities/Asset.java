package com.citi.portfolio.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="asset")
public class Asset implements Serializable {

    @Column(name="symbol")
    private String symbol;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="aid")
    private Integer aid;
    @Column(name="name")
    private String name;
    @Column(name="current_price")
    private int current_price;
    @Column(name="market_value")
    private double market_value;

    @Column(name="yield_percent")
    private float yield_percent;

    @Column(name="high")
    private int high;

    @Column(name="low")
    private int low;
    //Methods

    @Column(name="type")
    private String type;

    public Asset() {};

    public Asset(String symbol, Integer aid, String name, int current_price, long market_value, float yield_percent, int high, int low, String type) {
        this.symbol = symbol;
        this.aid = aid;
        this.name = name;
        this.current_price = current_price;
        this.market_value = market_value;
        this.yield_percent = yield_percent;
        this.high = high;
        this.low = low;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public float getYield_percent() {
        return yield_percent;
    }

    public void setYield_percent(float yield_percent) {
        this.yield_percent = yield_percent;
    }

    public double getMarket_value() {
        return market_value;
    }

    public void setMarket_value(double market_value) {
        this.market_value = market_value;
    }

    public int getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(int current_price) {
        this.current_price = current_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
