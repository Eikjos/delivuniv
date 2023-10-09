import { DeliveryPersonService } from 'src/app/services/delivery-person.service';
import { DeliveryPerson } from './../../models/delivery-person.model';
import { Component } from '@angular/core';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css'],
})
export class SearchPageComponent {
  search!: string;
  DeliveryPersons!: DeliveryPerson[];
  page!: number;
  pageSize!: number;
  isAvailable!: boolean;
  order!: 0 | 1;

  constructor(private service: DeliveryPersonService) {}

  ngOnInit(): void {
    this.DeliveryPersons = this.service.search('', 1, 10, true, 0).person;
    this.page = 1;
    this.pageSize = 10;
    this.isAvailable = true;
    this.order = 0;
  }

  onSubmit(): void {
    var result = this.service.search(
      this.search,
      1,
      10,
      this.isAvailable,
      this.order
    );
    this.DeliveryPersons = result.person;
    this.page = 1;
    this.pageSize = 10;
  }

  onChangePageSize(newValue: number): void {
    var result = this.service.search(
      this.search,
      1,
      newValue,
      this.isAvailable,
      this.order
    );
    this.DeliveryPersons = result.person;
    this.page = 1;
    this.pageSize = newValue;
  }
  onChangePage(newValue: number): void {
    var result = this.service.search(
      this.search,
      newValue,
      this.pageSize,
      this.isAvailable,
      this.order
    );
    this.DeliveryPersons = result.person;
    this.page = newValue;
  }

  onChangeFilterOrder(newValue: 0 | 1): void {
    var result = this.service.search(
      this.search,
      1,
      this.pageSize,
      this.isAvailable,
      newValue
    );
    this.DeliveryPersons = result.person;
    this.page = 1;
    this.order = newValue;
  }
  onChangeIsAvailable(newValue: boolean): void {
    var result = this.service.search(
      this.search,
      1,
      this.pageSize,
      newValue,
      this.order
    );
    this.DeliveryPersons = result.person;
    this.page = 1;
    this.isAvailable = newValue;
  }
}
