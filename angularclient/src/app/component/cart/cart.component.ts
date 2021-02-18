import { Component, OnInit, Output } from '@angular/core';
import { Pizza } from 'src/app/model/pizza';
import { CartService } from 'src/app/service/cart.service';
import { OrderService } from 'src/app/service/order.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  
  cart: Pizza[] = [];

  constructor(private cartService: CartService, private orderService: OrderService) { }

  ngOnInit(): void {
    this.cart = this.cartService.cart;
    
  }

  getSum(cart): number {
    let totalSum = 0;
    cart.forEach(element => {
      totalSum = totalSum + element.price
    });
    return totalSum;
  }

  addOne(item: Pizza) {
    this.cart.push(item);
  }

  removeOne(item: Pizza) {
    for (let i = 0; i < this.cart.length; i++) {
      if (item.id === this.cart[i].id) {
        this.cart.splice(i, 1);
      }
    } 
  }

  sendOrder(cart: Pizza[]) {
    this.orderService.sendOrder(cart);
    while (cart.length > 0) {
      cart.pop();
    }
  }

}
