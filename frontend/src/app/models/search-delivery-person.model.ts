import { DeliveryPerson } from 'src/app/models/delivery-person.model';

export interface SearchDeliveryPerson {
  data: DeliveryPerson[];
  itemsPerPage: number;
  itemCount: number;
  page: number;
  pageCount: number;
}
