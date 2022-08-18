package com.citi.portfolio.demo;

import javax.persistence.*;

@Entity
public class PortfolioAsset {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer product_id;

    private Integer pid;

    private Integer aid;

    private Float buy_price;

    private Integer shares;

    private Float total_cost;


    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }



    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Float getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(Float buy_price) {
        this.buy_price = buy_price;
    }

    public Float getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Float total_cost) {
        this.total_cost = total_cost;
    }
}
