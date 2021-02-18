import { Component, OnInit } from '@angular/core';
import { PizzaService } from 'src/app/api/api';
import { Pizza } from 'src/app/model/pizza';
import { CartService } from 'src/app/service/cart.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  pizzas$ = this.pizzaService.getPizzas();
  
  constructor(private pizzaService: PizzaService, private cartService: CartService) { }

  ngOnInit(): void {
  }

  addToCart(item: Pizza) {
    this.cartService.addToCart(item);
  }

}
