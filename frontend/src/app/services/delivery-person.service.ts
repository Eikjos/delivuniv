import { Injectable } from '@angular/core';
import { DeliveryPerson } from '../models/delivery-person.model';

@Injectable({
  providedIn: 'root',
})
export class DeliveryPersonService {
  constructor() {}

  public search(
    search: string,
    page: number,
    pageSize: number,
    isAvailable: boolean,
    orderResult: 0 | 1
  ): { person: DeliveryPerson[]; page: number; pagesize: number } {
    return {
      person: [],
      page: 1,
      pagesize: 1,
    };
  }
}
