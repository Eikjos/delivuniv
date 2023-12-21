import { Component, Input } from '@angular/core';
import { DeliveryPerson } from '../../models/delivery-person.model';

@Component({
  selector: 'app-delivery-person-card',
  templateUrl: './delivery-person-card.component.html',
})
export default class DeliveryPersonCardComponent {
  @Input() deliveryPerson!: DeliveryPerson;
}
