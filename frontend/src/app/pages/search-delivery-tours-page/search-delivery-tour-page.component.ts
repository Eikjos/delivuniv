import { Component, OnInit } from '@angular/core';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import DeliveryTourService from 'src/app/services/delivery-tour.service';

@Component({
  templateUrl: './search-delivery-tour-page.component.html',
})
export class SearchDeliveryTourPageComponent implements OnInit {
  date!: Date | null;
  deliveryTours!: DeliveryTour[];
  page!: number;
  pageSize!: number;
  nbPage!: Array<number>;
  nbItems!: number;

  constructor(private service: DeliveryTourService) {}

  ngOnInit(): void {
    this.date = null;
    this.page = 0;
    this.pageSize = 10;
    this.deliveryTours = [];
    this.service
      .search(
        this.date ? new Date(this.date).toISOString() : null,
        this.page,
        this.pageSize
      )
      .subscribe((items) => {
        this.deliveryTours = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }

  onFocus(evt: any): void {
    evt.target.type = 'date';
    if ('showPicker' in HTMLInputElement.prototype) {
      try {
        evt.target.showPicker();
      } catch (error) {
        console.log(error);
      }
    }
  }

  onBlur(evt: any): void {
    if (!evt.target.value) {
      evt.target.type = 'text';
    }
  }

  onChangeDate(): void {
    this.page = 0;
    this.service
      .search(
        this.date ? new Date(this.date).toISOString() : null,
        this.page,
        this.pageSize
      )
      .subscribe((items) => {
        this.deliveryTours = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }

  onChangePageSize(): void {
    this.page = 0;
    this.service
      .search(
        this.date ? new Date(this.date).toISOString() : null,
        this.page,
        this.pageSize
      )
      .subscribe((items) => {
        this.deliveryTours = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }
  onChangePage(newValue: number): void {
    this.page = newValue;
    this.service
      .search(
        this.date ? new Date(this.date).toISOString() : null,
        this.page,
        this.pageSize
      )
      .subscribe((items) => {
        this.deliveryTours = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }
}
