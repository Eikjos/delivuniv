import { Injectable } from '@angular/core';
import { SearchDeliveryPerson } from 'src/app/models/search-delivery-person.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeliveryPerson } from 'src/app/models/delivery-person.model';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import { SearchDeliveryTour } from 'src/app/models/search-delivery-tour.model';

@Injectable({
  providedIn: 'root',
})
export class DeliveryPersonService {
  constructor(private http: HttpClient) {}

  findAll = (): Observable<DeliveryPerson[]> =>
    this.http.get<DeliveryPerson[]>('delivery-persons');

  search = (
    search: string,
    page: number,
    pageSize: number,
    isAvailable: boolean,
    orderResult: 'dateAsc' | 'dateDesc' | 'toursAsc' | 'toursDesc',
    startDate: string | null,
    endDate: string | null
  ): Observable<SearchDeliveryPerson> => {
    if (startDate != null && endDate != null) {
      return this.http.get<SearchDeliveryPerson>('delivery-persons/search', {
        params: {
          search,
          page,
          itemsPerPage: pageSize,
          available: isAvailable,
          asc:
            orderResult === 'dateAsc'
              ? 'createdAt'
              : orderResult === 'toursAsc'
              ? 'deliveryTours'
              : '',
          desc:
            orderResult === 'dateDesc'
              ? 'createdAt'
              : orderResult === 'toursDesc'
              ? 'deliveryTours'
              : '',
          startDate,
          endDate,
        },
      });
    } else if (startDate == null && endDate != null) {
      return this.http.get<SearchDeliveryPerson>('delivery-persons/search', {
        params: {
          search,
          page,
          itemsPerPage: pageSize,
          available: isAvailable,
          asc:
            orderResult === 'dateAsc'
              ? 'createdAt'
              : orderResult === 'toursAsc'
              ? 'deliveryTours'
              : '',
          desc:
            orderResult === 'dateDesc'
              ? 'createdAt'
              : orderResult === 'toursDesc'
              ? 'deliveryTours'
              : '',
          endDate,
        },
      });
    } else if (startDate != null && endDate == null) {
      return this.http.get<SearchDeliveryPerson>('delivery-persons/search', {
        params: {
          search,
          page,
          itemsPerPage: pageSize,
          available: isAvailable,
          asc:
            orderResult === 'dateAsc'
              ? 'createdAt'
              : orderResult === 'toursAsc'
              ? 'deliveryTours'
              : '',
          desc:
            orderResult === 'dateDesc'
              ? 'createdAt'
              : orderResult === 'toursDesc'
              ? 'deliveryTours'
              : '',
          startDate,
        },
      });
    } else {
      return this.http.get<SearchDeliveryPerson>('delivery-persons/search', {
        params: {
          search,
          page,
          itemsPerPage: pageSize,
          available: isAvailable,
          asc:
            orderResult === 'dateAsc'
              ? 'createdAt'
              : orderResult === 'toursAsc'
              ? 'deliveryTours'
              : '',
          desc:
            orderResult === 'dateDesc'
              ? 'createdAt'
              : orderResult === 'toursDesc'
              ? 'deliveryTours'
              : '',
        },
      });
    }
  };

  getDeliveryTours = (
    id: number,
    page: number,
    pageSize: number,
    date: string | null
  ): Observable<SearchDeliveryTour> => {
    if (date == null) {
      return this.http.get<SearchDeliveryTour>(
        'delivery-persons/' + id + '/delivery-tours',
        {
          params: {
            page,
            itemsPerPage: pageSize,
          },
        }
      );
    }

    return this.http.get<SearchDeliveryTour>(
      'delivery-persons/' + id + '/delivery-tours',
      {
        params: {
          date,
          page,
          itemsPerPage: pageSize,
        },
      }
    );
  };

  getById = (id: number): Observable<DeliveryPerson> =>
    this.http.get<DeliveryPerson>('delivery-persons/' + id);

  delete = (id: number) => this.http.delete('delivery-persons/' + id);

  update = (deliveryPerson: DeliveryPerson) =>
    this.http.patch('delivery-persons/' + deliveryPerson.id, {
      name: deliveryPerson.name,
      available: deliveryPerson.available,
    });

  create = (name: string, available: boolean) =>
    this.http.post('delivery-persons', { name, available });
}
