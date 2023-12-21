import { Component, Input } from '@angular/core';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';

@Component({
  selector: 'app-delivery-tour-card',
  templateUrl: './delivery-tour-card.component.html',
})
export default class DeliveryTourCardComponent {
  @Input() deliveryTour!: DeliveryTour;
}
