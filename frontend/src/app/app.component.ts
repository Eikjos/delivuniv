import { Component, OnInit } from '@angular/core';
import { DeliveryPerson } from './models/delivery-person.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  myDeliveryPerson!: DeliveryPerson;

  ngOnInit(): void {
    this.myDeliveryPerson = {
      firstname: 'Thomas',
      lastname: 'Hamelin',
      isAvailable: true,
      createdDate: new Date(),
    };
  }
}
