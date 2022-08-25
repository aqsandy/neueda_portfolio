import { Component, OnInit } from '@angular/core';
import { MinLengthValidator } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap, Params } from '@angular/router'
import { ChartType } from 'chart.js';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})

export class StockComponent implements OnInit {
  stockMonthlyData: number[] = [238.31, 240.06, 240.54, 245.52, 247.50, 271.70, 272.24, 268.43, 258.85, 274.82, 280.89, 297.14, 297.27, 300.58, 307.39, 308.63, 288.17, 290.42, 283.33, 294.35, 286.63, 300.02, 309.32, 306.56, 303.99, 302.86, 296.66, 289.91, 296.45, 297.09]
  stockMonthlyLabels: string[] = ["2022-07-15", "2022-07-18", "2022-07-19", "2022-07-20", "2022-07-21", "2022-07-22", "2022-07-25", "2022-07-26", "2022-07-27", "2022-07-28", "2022-08-01", "2022-08-02", "2022-08-03", "2022-08-04", "2022-08-05", "2022-08-08", "2022-08-09", "2022-08-10", "2022-08-11", "2022-08-12", "2022-08-15", "2022-08-16", "2022-08-17", "2022-08-18", "2022-08-19", "2022-08-22", "2022-08-23", "2022-08-24", "2022-08-25"]

  stockWeeklyData: number[] = [306.56, 303.99, 302.86, 296.66, 289.91, 296.45, 297.09]
  stockWeeklyLabels: string[] = ["2022-08-17", "2022-08-18", "2022-08-19", "2022-08-22", "2022-08-23", "2022-08-24", "2022-08-25"]

  portfolioId: number = 1

  assetId: number = 0
  currentPrice: number = 0
  priceChange: number = 0
  percentageChange: number = 0
  marketValue: number = 0
  dayHigh: number = 0
  dayLow: number = 0
  openingPrice: number = 0
  previousClosingPrice: number = 0

  tradeAdvice: string = ''

  stockGraphDuration: string = 'week'
  stockSymbol: string = ''
  stockName: string = ''

  buyQuantity: number = 0
  buyCost: number = 0
  sellQuantity: number = 0
  sellPrice: number = 0

  chartData: any = []
  hourLinkColour: string = "#808080"
  dayLinkColour: string = "#808080"
  weekLinkColour: string = '#ffffff'
  monthLinkColour: string = '#808080'

  apiStockData = 'http://localhost:8080/asset/stock/'
  apiGraphData = 'https://3p7zu95yg3.execute-api.us-east-1.amazonaws.com/default/priceFeed2?ticker='

  apiBuy = 'http://localhost:8080/trade/buy'
  apiSell = 'http://localhost:8080/trade/sell'

  apiModifyPortfolio = 'http://localhost:8080/PortfolioAsset/modify'

  apiAdviceByTicker = 'https://qz4sxjl623.execute-api.us-east-1.amazonaws.com/default/tradeAdvisor?ticker='

  request = new XMLHttpRequest();

  isLoaded: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.onClickWeek();
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.stockSymbol = params.get('search')!
      console.log(params.get('search'))
    })

    const data = this.getStockData(this.stockSymbol)
    const graphData = this.getGraphData(this.stockSymbol)
    this.onClickWeek()
  }

  getStockData(ticker: string) {
    this.isLoaded = false;
    this.request.open('GET', this.apiStockData + ticker, false);
    this.request.send(null);
    let response = JSON.parse(this.request.responseText);
    let data = response.data
    console.log(data)

    this.assetId = data.aid
    this.stockName = data.name;
    this.currentPrice = data.current_price;
    this.percentageChange = data.yield_percent;
    this.dayHigh = data.high;
    this.dayLow = data.low;
    this.marketValue = data.market_value;
    this.openingPrice = Math.floor(Math.random() * (this.dayHigh - this.dayLow + 1)) + this.dayLow;
    this.previousClosingPrice = Math.floor(Math.random() * (this.dayHigh - this.dayLow + 1)) + this.dayLow;
    this.priceChange = this.currentPrice - this.openingPrice;

    this.request.open('GET', this.apiAdviceByTicker + ticker, false);
    this.request.send(null);
    response = JSON.parse(this.request.responseText);
    this.tradeAdvice = response.advice;
    this.isLoaded = true;
  }

  getGraphData(ticker: string) {
    this.request.open('GET', this.apiGraphData + ticker + '&num_days=30', false);
    this.request.send(null);
    if (this.request.status != 400) {
      let response = JSON.parse(this.request.responseText);
      let data = response.price_data

      console.log(data)
      this.stockMonthlyLabels = []
      this.stockMonthlyData = []
      for (let item of data){
        this.stockMonthlyLabels.push(item.name);
        this.stockMonthlyData.push(item.value.toFixed(2));
      }

      this.stockWeeklyLabels = this.stockMonthlyLabels.slice(-7);
      this.stockWeeklyData = this.stockMonthlyData.slice(-7);
    }
  }


  onBuyQuantityChange(arg: number) {
    this.buyCost = this.currentPrice * arg
  }

  onSellQuantityChange(arg: number) {
    this.sellPrice = -Math.abs(this.currentPrice * arg)
  }

  public confirmBuy() {

    var request = new XMLHttpRequest();

    request.open('POST', this.apiBuy);
    request.setRequestHeader("Content-Type", "application/json");

    request.onreadystatechange = function () {
      if (request.readyState === 4) {
        console.log(request.status);
        console.log(request.responseText);
      }
    }

    request.send(JSON.stringify({'ticker' : this.stockSymbol, 'numShares': this.buyQuantity}));

    request.open('PUT', 'http://localhost:8080/PortfolioAsset/modify');
    request.setRequestHeader("Content-Type", "application/json");

    request.onreadystatechange = function () {
      if (request.readyState === 4) {
        console.log(request.status);
        console.log(request.responseText);
      }
    }

    request.send(JSON.stringify({ 'pid': this.portfolioId, 'aid': this.assetId, 'shares': this.buyQuantity, 'total_cost': this.buyCost }));
    let response = JSON.parse(this.request.responseText);

  }


  public confirmSell() {
    var request = new XMLHttpRequest();

    request.open('POST', this.apiSell);
    request.setRequestHeader("Content-Type", "application/json");

    request.onreadystatechange = function () {
      if (request.readyState === 4) {
        console.log(request.status);
        console.log(request.responseText);
      }
    }

    request.send(JSON.stringify({'ticker' : this.stockSymbol, 'numShares': this.sellQuantity}));

    request.open('PUT', this.apiModifyPortfolio);
    request.setRequestHeader("Content-Type", "application/json");

    request.onreadystatechange = function () {
      if (request.readyState === 4) {
        console.log(request.status);
        console.log(request.responseText);
      }
    }

    request.send(JSON.stringify({ 'pid': this.portfolioId, 'aid': this.assetId, 'shares': -Math.abs(this.sellQuantity), 'total_cost': this.sellPrice }));
    let response = JSON.parse(this.request.responseText);
    console.log(response)


  }

  public chartType: ChartType = 'line';

  public chartDatasets: Array<any> = [
    { data: [this.stockWeeklyData], label: this.stockSymbol, lineTension: 0 }
  ];

  public chartLabels: Array<any> = [this.stockWeeklyLabels];

  public chartColors: Array<any> = [
    {
      backgroundColor: 'rgba(226, 82, 82, .2)',
      borderColor: '#e25252',
      borderWidth: 2,
    }
  ];

  public chartOptions: any = {
    responsive: true,
    hover: {
      mode: 'nearest',
      intersec: true,
    },
    scales: {
      yAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'VALUE ($)',
          fontColor: "#fff",
          fontSize: 18,
          fontFamily: "Manrope",
        },
        gridLines: {
          color: "#222"
        }
      }],
      xAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'MONTH',
          fontColor: "#fff",
          fontSize: 18,
          fontFamily: "Manrope",
        },
        gridLines: {
          color: "#222"
        }
      }]
    },
    title: {
      display: true,
      text: 'HISTORICAL STOCK PRICE',
      fontColor: "#fff",
      fontSize: 25,
      fontFamily: "Manrope",
    },
  };

  onClickWeek() {
    this.stockGraphDuration = 'week'
    this.chartLabels = this.stockWeeklyLabels

    this.chartData = this.stockWeeklyData
    this.chartDatasets = [{ data: this.chartData, label: this.stockSymbol, lineTension: 0 }]

    this.hourLinkColour = "#808080"
    this.dayLinkColour = "#808080"
    this.weekLinkColour = "#ffffff"
    this.monthLinkColour = "#808080"

    console.log(this.chartLabels)
    console.log(this.chartData)
  }

  onClickMonth() {
    this.stockGraphDuration = 'week'
    this.chartLabels = this.stockMonthlyLabels

    this.chartData = this.stockMonthlyData
    this.chartDatasets = [{ data: this.chartData, label: this.stockSymbol, lineTension: 0 }]

    this.hourLinkColour = "#808080"
    this.dayLinkColour = "#808080"
    this.weekLinkColour = "#808080"
    this.monthLinkColour = "#ffffff"

    console.log(this.chartLabels)
    console.log(this.chartData)
  }


  public getChartData() { }
  public changeChartDuration() { }
  public chartClicked(e: any): void { }
  public chartHovered(e: any): void { }

}