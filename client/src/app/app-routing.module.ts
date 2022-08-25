import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { MarketComponent } from './market/market.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { HistoryComponent } from './history/history.component';
import { StockComponent } from './stock/stock.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [{path: 'home', component: HomeComponent},
                        {path: 'my-portfolio', component: PortfolioComponent},
                        {path: 'market', component: MarketComponent},
                        {path: 'history', component: HistoryComponent},
                        {path: 'stocks/:search', component: StockComponent},
                        {path: '**', redirectTo: 'home', pathMatch: 'full'}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
