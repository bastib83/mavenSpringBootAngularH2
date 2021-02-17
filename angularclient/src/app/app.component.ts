import { Component } from '@angular/core';
import { PizzaService } from './api/pizza.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frontend';
  pizzas$ = this.pizzaService.getPizzas();

  constructor(private readonly pizzaService: PizzaService) { }
}
