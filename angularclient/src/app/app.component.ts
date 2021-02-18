import { Component } from '@angular/core';
import { Pizza } from './model/pizza';
import { CartService } from './service/cart.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frontend';
  cart: Pizza[] = [];

    constructor(private cartService: CartService) {}

    ngOnInit(): void {
      this.cart = this.cartService.cart;
      this.cartService.item$.subscribe(data => { 
        this.cart.push(data)
      });
    }

  }

