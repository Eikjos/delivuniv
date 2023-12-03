import { DeliveryPersonService } from 'src/app/services/delivery-person.service';
import { DeliveryPerson } from './../../models/delivery-person.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
})
export class SearchPageComponent implements OnInit {
  search!: string;
  deliveryPersons!: DeliveryPerson[];
  page!: number;
  pageSize!: number;
  isAvailable!: boolean;
  startDate!: Date | null;
  endDate!: Date | null;
  order!: 'ORDER_BY_CREATED_DATE_ASC' | 'ORDER_BY_CREATED_DATE_DESC';
  nbPage!: Array<number>;
  nbItems!: number;

  constructor(private service: DeliveryPersonService) {}

  ngOnInit(): void {
    this.search = '';
    this.page = 0;
    this.isAvailable = true;
    this.pageSize = 10;
    this.order = 'ORDER_BY_CREATED_DATE_ASC';
    this.deliveryPersons = [];
    this.service
      .search(
        this.search,
        this.page,
        this.pageSize,
        this.isAvailable,
        this.order,
        this.startDate ? new Date(this.startDate).toUTCString() : null,
        this.endDate ? new Date(this.endDate).toUTCString() : null
      )
      .subscribe((items) => {
        this.deliveryPersons = items.data;
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
  onSubmit(): void {
    this.service
      .search(this.search, 0, 10, true, 'ORDER_BY_CREATED_DATE_ASC', '', '')
      .subscribe((items) => {
        this.deliveryPersons = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
    this.isAvailable = true;
    this.page = 0;
    this.pageSize = 10;
    this.order = 'ORDER_BY_CREATED_DATE_ASC';
    this.startDate = null;
    this.endDate = null;
  }

  onChangePageSize(): void {
    this.page = 0;
    this.service
      .search(
        this.search,
        this.page,
        this.pageSize,
        this.isAvailable,
        this.order,
        this.startDate ? new Date(this.startDate).toISOString() : null,
        this.endDate ? new Date(this.endDate).toISOString() : null
      )
      .subscribe((items) => {
        this.deliveryPersons = items.data;
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
        this.search,
        this.page,
        this.pageSize,
        this.isAvailable,
        this.order,
        this.startDate ? new Date(this.startDate).toISOString() : null,
        this.endDate ? new Date(this.endDate).toISOString() : null
      )
      .subscribe((items) => {
        this.deliveryPersons = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }

  onChangeFilterOrder(): void {
    this.page = 0;
    this.service
      .search(
        this.search,
        this.page,
        this.pageSize,
        this.isAvailable,
        this.order,
        this.startDate ? new Date(this.startDate).toISOString() : '',
        this.endDate ? new Date(this.endDate).toISOString() : ''
      )
      .subscribe((items) => {
        this.deliveryPersons = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }

  onChangeIsAvailable(): void {
    this.page = 0;
    this.service
      .search(
        this.search,
        this.page,
        this.pageSize,
        this.isAvailable,
        this.order,
        this.startDate ? new Date(this.startDate).toISOString() : null,
        this.endDate ? new Date(this.endDate).toISOString() : null
      )
      .subscribe((items) => {
        this.deliveryPersons = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }

  onChangeStartDate(): void {
    this.page = 0;
    this.service
      .search(
        this.search,
        this.page,
        this.pageSize,
        this.isAvailable,
        this.order,
        this.startDate ? new Date(this.startDate).toISOString() : null,
        this.endDate ? new Date(this.endDate).toISOString() : null
      )
      .subscribe((items) => {
        this.deliveryPersons = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }

  onChangeEndDate(): void {
    this.page = 0;
    this.service
      .search(
        this.search,
        this.page,
        this.pageSize,
        this.isAvailable,
        this.order,
        this.startDate ? new Date(this.startDate).toISOString() : null,
        this.endDate ? new Date(this.endDate).toISOString() : null
      )
      .subscribe((items) => {
        this.deliveryPersons = items.data;
        this.nbItems = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }
}
