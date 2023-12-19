import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import { Delivery } from 'src/app/models/delivery.model';
import { SearchDeliveryTour } from 'src/app/models/search-delivery-tour.model';
import { SearchDelivery } from 'src/app/models/search-delivery.model';

@Injectable({
  providedIn: 'root',
})
export default class DeliveryService {
  constructor(private http: HttpClient) {}

  findAll = (page: number, pageSize: number) =>
    this.http.get<SearchDelivery>('deliveries', {
      params: {
        page,
        itemsPerPage: pageSize,
      },
    });

  create = (
    pickUpAddress: string,
    deliveryAddress: string,
    deliveryTourId: number
  ) =>
    this.http.post<Delivery>('deliveries', {
      pickUpAddress,
      deliveryAddress,
      deliveryTourId,
    });

  getById = (id: number) => this.http.get<Delivery>('deliveries/' + id);

  update = (id: number) => this.http.put('deliveries/' + id, {});

  delete = (id: number) => this.http.delete('deliveries/' + id);
}
