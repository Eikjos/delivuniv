import { DeliveryTour } from 'src/app/models/delivery-tour.model';

export interface SearchDeliveryTour {
  data: DeliveryTour[];
  itemsPerPage: number;
  itemCount: number;
  page: number;
  pageCount: number;
}
