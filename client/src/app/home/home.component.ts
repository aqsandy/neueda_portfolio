import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  apiJoke = 'http://localhost:8080/joke'
  request = new XMLHttpRequest();
  joke: string = '';

  public image: any

  constructor() { }

  ngOnInit(): void {
    this.getJoke();
  }

  getJoke() {
    this.request.open('GET', this.apiJoke, false);
    this.request.send(null);
    var response = this.request.responseText;
    console.log(response)
    this.joke = response;
  }

}
