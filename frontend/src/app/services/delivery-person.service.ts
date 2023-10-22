import { Injectable } from '@angular/core';
import { SearchDeliveryPerson } from 'src/app/models/search-delivery-person.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeliveryPerson } from 'src/app/models/delivery-person.model';

@Injectable({
  providedIn: 'root',
})
export class DeliveryPersonService {
  constructor(private http: HttpClient) {}

  search = (
    search: string,
    page: number,
    pageSize: number,
    isAvailable: boolean,
    orderResult: 'ORDER_BY_CREATED_DATE_ASC' | 'ORDER_BY_CREATED_DATE_DESC'
  ): Observable<SearchDeliveryPerson> =>
    this.http.get<SearchDeliveryPerson>('delivery-person/search', {
      params: {
        search,
        page,
        itemsPerPage: pageSize,
        available: isAvailable,
        order: orderResult,
      },
    });

  getById = (id: number): Observable<DeliveryPerson> =>
    this.http.get<DeliveryPerson>('delivery-person/' + id);

  delete = (id: number) => this.http.delete('delivery-person/' + id);

  update = (deliveryPerson: DeliveryPerson) =>
    this.http.patch('delivery-person/' + deliveryPerson.id, {
      name: deliveryPerson.name,
      available: deliveryPerson.available,
    });

  create = (name: string, available: boolean) =>
    this.http.post('delivery-person', { name, available });
}
