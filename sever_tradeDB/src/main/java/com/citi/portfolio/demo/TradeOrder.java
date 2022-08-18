package com.citi.portfolio.demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="order_history")
public class TradeOrder implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="order_id")
    private Integer orderid;

    @Column(name="order_date")
    private String orderDate;

    @Column(name="order_type")
    private String orderType;


    @Column(name="num_shares")
    private int numShares;

    @Column(name="net_amount")
    private int netAmount;

    @Column(name="order_ticker")
    private long orderTicker;

    public TradeOrder(Integer orderid, String orderDate, String orderType, int numShares, int netAmount, long orderTicker) {
        this.orderid = orderid;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.numShares = numShares;
        this.netAmount = netAmount;
        this.orderTicker = orderTicker;
    }

    public TradeOrder() {};

    public long getOrderTicker() {
        return orderTicker;
    }

    public void setOrderTicker(long orderTicker) {
        this.orderTicker = orderTicker;
    }

    public int getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(int netAmount) {
        this.netAmount = netAmount;
    }

    public int getNumShares() {
        return numShares;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
}
