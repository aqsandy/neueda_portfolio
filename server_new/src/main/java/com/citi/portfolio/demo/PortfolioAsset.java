package com.citi.portfolio.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class PortfolioAsset {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer product_id;

    private Integer pid;

    private Integer aid;

    private Float buyprice;

    private Integer shares;

    private Float totalcost;


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

    public Float getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(Float buyprice) {
        this.buyprice = buyprice;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Float getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Float totalcost) {
        this.totalcost = totalcost;
    }
}
