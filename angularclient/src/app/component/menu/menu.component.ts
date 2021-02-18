import { Component, OnInit } from '@angular/core';
import { PizzaService } from 'src/app/api/api';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  pizzas$ = this.pizzaService.getPizzas();
  
  constructor(private pizzaService: PizzaService) { }

  ngOnInit(): void {
  }

}
