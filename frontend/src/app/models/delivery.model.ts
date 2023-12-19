import { DeliveryTour } from 'src/app/models/delivery-tour.model';

export interface Delivery {
  id: number;
  pickUpAddress: string;
  deliveryAddress: string;
  deliveryTour: DeliveryTour;
}
