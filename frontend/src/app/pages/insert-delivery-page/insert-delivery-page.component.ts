import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import DeliveryTourService from 'src/app/services/delivery-tour.service';
import DeliveryService from 'src/app/services/delivery.service';

@Component({
  templateUrl: './insert-delivery-page.component.html',
})
export class InsertDeliveryComponent implements OnInit {
  submitted!: boolean;
  deliveryTours!: DeliveryTour[];
  form!: FormGroup;
  pickUpAddressControl!: FormControl;
  deliveryAddressControl!: FormControl;

  constructor(
    private deliveryService: DeliveryService,
    private deliveryTourService: DeliveryTourService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.submitted = false;
    this.pickUpAddressControl = new FormControl(null, [Validators.required]);
    this.deliveryAddressControl = new FormControl(null, [Validators.required]);
    this.form = new FormGroup({
      pickUpAddress: this.pickUpAddressControl,
      deliveryAddress: this.deliveryAddressControl,
      deliveryTourId: new FormControl(),
    });
    this.deliveryTourService.findAll().subscribe((items) => {
      this.deliveryTours = items;
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (
      this.form.value.pickUpAddress != null &&
      this.form.value.deliveryAddress != null
    ) {
      this.deliveryService
        .create(
          this.form.value.pickUpAddress,
          this.form.value.deliveryAddress,
          this.form.value.deliveryTourId
        )
        .subscribe((item) => {
          this.router.navigate(['/deliveries']);
        });
    }
  }

  onBack(): void {
    history.back();
  }
}
