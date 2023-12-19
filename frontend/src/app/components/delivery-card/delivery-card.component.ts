import { Component, Input } from '@angular/core';
import { Delivery } from '../../models/delivery.model';

@Component({
  selector: 'app-delivery-card',
  templateUrl: './delivery-card.component.html',
})
export default class DeliveryCardComponent {
  @Input() delivery!: Delivery;
}
