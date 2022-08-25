import { Component, OnInit, NgModule } from '@angular/core';
import { ChartDataSets, ChartType, ChartOptions } from 'chart.js';
import { MultiDataSet, Color, Label, PluginServiceGlobalRegistrationAndOptions } from 'ng2-charts';
import { HttpClient } from '@angular/common/http';
import { Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})

export class PortfolioComponent implements OnInit {
  apiAllAssets = 'http://localhost:8080/PortfolioAsset/all'
  apiAssetByID = 'http://localhost:8080/asset/'
  apiAdviceByTicker = 'https://qz4sxjl623.execute-api.us-east-1.amazonaws.com/default/tradeAdvisor?ticker='

  portfolioAssets: string[][] = []
  portfolioAssetIds: number[] = []

  request = new XMLHttpRequest();

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAllAssets();
  }

  getAllAssets() {
    this.request.open('GET', this.apiAllAssets, false);
    this.request.send(null);
    var response = JSON.parse(this.request.responseText);
    var data = response.data

    for(let item of data) {
      var assetId = item.aid;
      this.portfolioAssetIds.push(assetId);
      this.getAssetInfoByID(assetId, item.shares); 
    }
  }

  getAssetInfoByID(assetID: string, numShares: number) {
    this.request.open('GET', this.apiAssetByID + assetID, false);
    this.request.send(null);
    var response = JSON.parse(this.request.responseText);

    var data = response.data
    console.log(response)
    var asset: string[] = [data.name, data.symbol, data.type, numShares]

    this.getAdviceByTicker(data.symbol, asset)

    this.portfolioAssets.push(asset);
    console.log(this.portfolioAssets)
  }

  getAdviceByTicker(ticker: string, asset: string[]) {
    this.request.open('GET', this.apiAdviceByTicker + ticker, false);
    this.request.send(null);
    var response = JSON.parse(this.request.responseText);
    var advice = response.advice;
    console.log(advice)
    asset.push(advice)
  }

  routeToAsset(ticker: string){
    this.router.navigate(['/stocks/' + ticker]);
  }

  lineChartData: ChartDataSets[] = [
    { data: [85, 72, 78, 75, 77, 75], label: 'Net Worth', lineTension: 0 },
  ];
  lineChartLabels: Label[] = ['January', 'February', 'March', 'April', 'May', 'June'];
  lineChartOptions = {
    responsive: true,
    maintainAspectRatio: false
  };
  lineChartColors: Color[] = [
    {
      borderColor: 'rgba(104,243,169)',
      backgroundColor: 'rgba(104,243,169, 0.25)',
    },
  ];
  chartLegend = false;
  lineChartPlugins = [];
  lineChartType: ChartType = 'line';

  doughnutChartType: ChartType = 'doughnut';

  incomeChartLabels: Label[] = ['Salary', 'Side Hustle', 'Gifts'];
  incomeChartData: MultiDataSet = [
    [55, 25, 20]
  ];

  spendingChartLabels: Label[] = ['Rent', 'Entertainment', 'Food', 'Utilities'];
  spendingChartData: MultiDataSet = [
    [30, 25, 20, 25]
  ];

  // events
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

}


