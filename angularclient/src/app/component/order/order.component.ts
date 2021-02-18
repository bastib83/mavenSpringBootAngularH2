import { Component, OnInit } from '@angular/core';
import { Pizza } from 'src/app/model/pizza';
import { OrderService } from 'src/app/service/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orderList = [];
  selectedItemFromList: Pizza;

  constructor(private oderService: OrderService) { }

  ngOnInit(): void {
    this.orderList = this.oderService.orderList; //bad workaround! -> falsche variante
    this.oderService.itemsChange$.subscribe(data => { this.orderList.push(data) });
  }

  selectItemFromList(item: Pizza) {
    this.selectedItemFromList = item;
  }

  complete(item: Pizza) {
    item.complete = true;
    //this.oderService.completeOrder(item);

    for (let i = 0; i < this.orderList.length; i++) {
      if (item.id === this.orderList[i].id) {
        this.orderList.splice(i, 1);
      }
    }
    this.selectedItemFromList = undefined;
  }

}
