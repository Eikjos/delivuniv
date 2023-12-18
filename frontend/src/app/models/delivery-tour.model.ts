import { DeliveryPerson } from 'src/app/models/delivery-person.model';

export interface DeliveryTour {
  id: number;
  name: string;
  startDate: Date;
  endDate: Date;
  deliveryPerson: DeliveryPerson;
}
