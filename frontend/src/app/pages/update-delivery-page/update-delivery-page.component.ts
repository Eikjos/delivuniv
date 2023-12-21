import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DeliveryPerson } from 'src/app/models/delivery-person.model';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import { Delivery } from 'src/app/models/delivery.model';
import { DeliveryPersonService } from 'src/app/services/delivery-person.service';
import DeliveryTourService from 'src/app/services/delivery-tour.service';
import DeliveryService from 'src/app/services/delivery.service';

@Component({
  templateUrl: './update-delivery-page.component.html',
})
export class UpdateDeliveryComponent implements OnInit {
  submitted!: boolean;
  delivery!: Delivery;
  deliveryTours!: DeliveryTour[];
  form!: FormGroup;
  pickUpAddressControl!: FormControl;
  deliveryAddressControl!: FormControl;
  deliveryTourControl!: FormControl;

  constructor(
    private deliveryTourService: DeliveryTourService,
    private deliveryService: DeliveryService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.submitted = false;
    this.deliveryService
      .getById(this.route.snapshot.params['id'])
      .subscribe((item) => {
        if (item == null) {
          this.router.navigate(['/']);
        }
        this.delivery = item;
        this.deliveryTourService.findAll().subscribe((items) => {
          this.deliveryTours = items;
          this.pickUpAddressControl = new FormControl(item.pickUpAddress, [
            Validators.required,
          ]);
          this.deliveryAddressControl = new FormControl(item.deliveryAddress, [
            Validators.required,
          ]);
          this.deliveryTourControl = new FormControl(item.deliveryTour.id);
          this.form = new FormGroup({
            pickUpAddress: this.pickUpAddressControl,
            deliveryAddress: this.deliveryAddressControl,
            deliveryTourId: this.deliveryTourControl,
          });
        });
      });
  }

  onSubmit(): void {
    if (
      this.form.value.pickUpAddress != null &&
      this.form.value.deliveryAddress != null
    ) {
      this.submitted = true;
      this.deliveryTourService
        .update(
          this.delivery.id,
          this.form.value.pickUpAddress,
          this.form.value.deliveryAddress,
          this.form.value.deliveryTourId
        )
        .subscribe({
          next: (item) => this.router.navigate(['/deliveries']),
        });
    }
  }

  onBack(): void {
    history.back();
  }
}
