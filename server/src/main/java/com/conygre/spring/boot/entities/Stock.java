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
@Entity @Table(name="Stocks")
public class Stock implements Serializable {

  //Instance variables
  @Column(name="symbol") private String symbol;


  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="StockID") private Integer StockID;

  @Column(name="ExchangeName") private String ExchangeName;
  @Column(name="ask_price") private int AskPrice;
  @Column(name="market_cap") private long MarketCap;
  @Column(name="yield_percent") private double Yield;
  @Column(name="average_volume") private long average_volume;
  @Column(name="high") private int high;
  @Column(name="low") private int low;
  //Methods



  //constructors
  public Stock(){}

  public Stock(int StockID, String ExchangeName, String symbol, int AskPrice, long MarketCap, double Yield,
               long average_volume, int high, int low){
    this.StockID = StockID;
    this.ExchangeName=ExchangeName;
    this.symbol = symbol;
    this.AskPrice = AskPrice;
    this.MarketCap = MarketCap;
    this.Yield = Yield;
    this.average_volume = average_volume;
    this.high = high;
    this.low = low;
  }


  public int getStockID() {
    return StockID;
  }

  public String getSymbol(int StockID) {return symbol;}
  public String getExchangeName() {
    return ExchangeName;
  }
  public int getAskPrice() {
    return AskPrice;
  }

  public long getMarketCap() {
    return MarketCap;
  }

  public double getYield() {
    return Yield;
  }

  public long getAverage_volume() {
    return average_volume;
  }

  public int getHigh() {
    return high;
  }

  public int getLow() {
    return low;
  }
}