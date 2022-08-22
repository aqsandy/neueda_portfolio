package com.citi.portfolio.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="order_history")
public class TradeOrder implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    private String orderTicker;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="purchase_type")
    private String purchaseType;

    public TradeOrder() {};

    public String getOrderTicker() {
        return orderTicker;
    }

    public void setOrderTicker(String orderTicker) {
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }
}
