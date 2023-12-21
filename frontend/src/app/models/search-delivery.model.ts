import { Delivery } from 'src/app/models/delivery.model';

export interface SearchDelivery {
  data: Delivery[];
  itemsPerPage: number;
  itemCount: number;
  page: number;
  pageCount: number;
}
