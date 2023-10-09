import { DeliveryPersonService } from '../../services/delivery-person.service';
import { Component, Input, OnInit } from '@angular/core';
import { DeliveryPerson } from '../../models/delivery-person.model';

@Component({
  selector: 'app-delivery-person-card',
  templateUrl: './delivery-person-card.component.html',
  styleUrls: ['./delivery-person-card.component.css'],
})
export class DeliveryPersonCardComponent implements OnInit {
  @Input() deliveryPerson!: DeliveryPerson;

  constructor(private deliveryPersonService: DeliveryPersonService) {}
  ngOnInit(): void {}
}
