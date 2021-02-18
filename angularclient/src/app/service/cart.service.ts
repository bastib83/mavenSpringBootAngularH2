import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Pizza } from '../model/pizza';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  item$ = new Subject<Pizza>();
  cart: Pizza[] = [];

  constructor() { }

  public getCart(observe?: 'body', reportProgress?: boolean): Observable<Pizza> {
    return this.item$;
  }


  addToCart(item: Pizza) {
   this.item$.next(item);
  }

}
