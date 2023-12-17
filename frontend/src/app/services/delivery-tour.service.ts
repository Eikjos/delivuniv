import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import { SearchDeliveryTour } from 'src/app/models/search-delivery-tour.model';

@Injectable({
  providedIn: 'root',
})
export default class DeliveryTourService {
  constructor(private http: HttpClient) {}

  search = (
    date: string | null,
    page: number,
    pageSize: number
  ): Observable<SearchDeliveryTour> => {
    if (date == null) {
      return this.http.get<SearchDeliveryTour>('delivery-tours', {
        params: {
          page,
          itemsPerPage: pageSize,
        },
      });
    }

    return this.http.get<SearchDeliveryTour>('delivery-tours', {
      params: {
        date,
        page,
        itemsPerPage: pageSize,
      },
    });
  };

  create = (name: string, startDate: Date, endDate: Date, personId?: number) =>
    this.http.post<DeliveryTour>('delivery-tours', {
      name,
      startDate: new Date(startDate).toISOString(),
      endDate: new Date(endDate).toISOString(),
      deliveryPersonId: personId,
    });

  getById = (id: number) => this.http.get<DeliveryTour>('delivery-tours/' + id);

  update = (
    id: number,
    name: string,
    startDate: Date,
    endDate: Date,
    personId?: number
  ) =>
    this.http.put('delivery-tours/' + id, {
      name,
      startDate: new Date(startDate).toISOString(),
      endDate: new Date(endDate).toISOString(),
      deliveryPersonId: personId,
    });

  delete = (id: number) => this.http.delete('delivery-tours/' + id);
}
