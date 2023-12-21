import { Component, OnInit } from '@angular/core';
import { Delivery } from 'src/app/models/delivery.model';
import DeliveryService from 'src/app/services/delivery.service';

@Component({
  templateUrl: './search-delivery-page.component.html',
})
export class SearchDeliveryPageComponent implements OnInit {
  deliveries!: Delivery[];
  page!: number;
  pageSize!: number;
  nbPage!: Array<number>;
  nbItems!: number;

  constructor(private service: DeliveryService) {}

  ngOnInit(): void {
    this.page = 0;
    this.pageSize = 10;
    this.deliveries = [];
    this.service.findAll(this.page, this.pageSize).subscribe((items) => {
      this.deliveries = items.data;
      this.nbItems = items.itemCount;
      this.nbPage = Array(items.pageCount)
        .fill(1)
        .map((x, i) => i + 1);
    });
  }

  onChangePageSize(): void {
    this.page = 0;
    this.service.findAll(this.page, this.pageSize).subscribe((items) => {
      this.deliveries = items.data;
      this.nbItems = items.itemCount;
      this.nbPage = this.nbPage = Array(items.pageCount)
        .fill(1)
        .map((x, i) => i + 1);
    });
  }
  onChangePage(newValue: number): void {
    this.page = newValue;
    this.service.findAll(this.page, this.pageSize).subscribe((items) => {
      this.deliveries = items.data;
      this.nbItems = items.itemCount;
      this.nbPage = this.nbPage = Array(items.pageCount)
        .fill(1)
        .map((x, i) => i + 1);
    });
  }
}
