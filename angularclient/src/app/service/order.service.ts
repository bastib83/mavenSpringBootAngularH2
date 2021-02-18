import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Pizza } from '../model/pizza';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  itemsChange$ = new Subject<Pizza>();
  orderList: Pizza[] = [];
  
  constructor() { }

  sendOrder(itemList: Pizza[]) {
    itemList.forEach(element => {
      this.orderList.push(element);
    });
    //this.itemsChange$.next();
  }
}
