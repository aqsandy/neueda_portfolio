import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css'],
})
export class MarketComponent implements OnInit {

  marketStatus = "OPEN";

  apiBuyAsset = 'http://localhost:8080/trade/buy?'
  apiSellAsset = 'http://localhost:8080/trade/sell?'

  request = new XMLHttpRequest();
  
  constructor(
  ) { }

  ngOnInit(): void {
  }

  buyAsset(){
    let ticker = document.getElementById("stockToBuy");
    let quantity = document.getElementById("quantityStockToBuy");

    this.request.open('GET', this.apiBuyAsset + "ticker=" + ticker + "&numShares=" + quantity , false);
    this.request.send(null);
    let response = JSON.parse(this.request.responseText);
    console.log(response)
    let data = response.data



  }

  sellAsset(){
    let ticker = document.getElementById("stockToSell");
    let quantity = document.getElementById("quantityStockToSell");

    this.request.open('GET', this.apiBuyAsset + "ticker=" + ticker + "&numShares=" + quantity , false);
    this.request.send(null);
    let response = JSON.parse(this.request.responseText);
    console.log(response)
    let data = response.data
  }

}
