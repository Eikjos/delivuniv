import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DeliveryPerson } from 'src/app/models/delivery-person.model';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import { DeliveryPersonService } from 'src/app/services/delivery-person.service';
import DeliveryTourService from 'src/app/services/delivery-tour.service';

@Component({
  templateUrl: './insert-delivery-tour-page.component.html',
})
export class InsertDeliveryTourComponent implements OnInit {
  submitted!: boolean;
  startDate!: Date;
  endDate!: Date;
  name!: string;
  available!: boolean;
  deliveryPersons!: DeliveryPerson[];
  deliveryPersonId!: number;
  form!: FormGroup;
  nameControl!: FormControl;
  startControl!: FormControl;
  endControl!: FormControl;
  error: string | undefined;

  constructor(
    private deliveryTourService: DeliveryTourService,
    private deliveryPersonService: DeliveryPersonService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.submitted = false;
    this.available = true;
    this.nameControl = new FormControl(null, [Validators.required]);
    this.startControl = new FormControl(null, [Validators.required]);
    this.endControl = new FormControl(null, [Validators.required]);
    this.deliveryPersonService.findAll().subscribe((items) => {
      this.deliveryPersons = items;
    });
    this.form = new FormGroup({
      name: this.nameControl,
      startDate: this.startControl,
      endDate: this.endControl,
      deliveryPersonId: new FormControl(),
    });
  }

  onFocus(evt: any): void {
    evt.target.type = 'date';
    if ('showPicker' in HTMLInputElement.prototype) {
      try {
        evt.target.showPicker();
      } catch (error) {
        console.log(error);
      }
    }
  }

  onBlur(evt: any): void {
    if (!evt.target.value) {
      evt.target.type = 'text';
    }
  }

  onChangePerson(event: any) {
    this.deliveryPersonId = event.target.value;
  }

  onSubmit(): void {
    if (
      this.form.value.name != null &&
      this.form.value.startDate != null &&
      this.form.value.endDate != null
    ) {
      this.submitted = true;
      this.deliveryTourService
        .create(
          this.form.value.name,
          this.form.value.startDate,
          this.form.value.endDate,
          this.form.value.deliveryPersonId
        )
        .subscribe({
          next: (item) => this.router.navigate(['/delivery-tours']),
          error: (err) => {
            if (err.error.message == 'DELIVERY_PERSON_ALREADY_DELIVERY_TOUR') {
              this.error =
                'Le livreur sélectionné possède déjà une livraison dans cette intervalle de date';
            }
          },
        });
    }
  }

  onChangeStartDate(event: any) {
    this.startDate = event.target.value;
  }

  onBack(): void {
    history.back();
  }
}
