import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})

export class HistoryComponent implements OnInit {

  apiAllOrders = 'http://localhost:8080/trade/all'
  apiUpdateOrders = 'http://localhost:8080/trade/update'
  interval: any;

  request = new XMLHttpRequest();

  orders: string[][] = []
  columns = ['orderid', 'netAmount', 'numShares', 'orderDate', 'orderStatus', 'orderTicker'];

  constructor(
  ) { }

  ngOnInit(): void {
    this.getAllOrders();

    this.interval = setInterval( () => {
      this.updateHistory();
      this.ngOnInit();
    }, 30000);
  }

  getAllOrders() {
    this.orders = [];
    this.request.open('GET', this.apiAllOrders, false);
    this.request.send(null);
    var response = JSON.parse(this.request.responseText);
    console.log(response)
    var data = response.data
    

    for(let item of data) {
      var order: string[] = [item.orderid, item.orderType.toUpperCase(), item.orderTicker, item.numShares, item.netAmount, item.purchaseType, item.orderStatus, item.orderDate]
      this.orders.push(order)
    }
    console.log(this.orders)
  }

  updateHistory() {
    this.request.open('PUT', this.apiUpdateOrders, false);
    this.request.send(null);
    var response = this.request.responseText;
    var status = this.request.status;
    console.log(status + response)
  }
}
