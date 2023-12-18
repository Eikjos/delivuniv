import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DeliveryPerson } from 'src/app/models/delivery-person.model';
import { DeliveryPersonService } from 'src/app/services/delivery-person.service';

@Component({
  templateUrl: './update-delivery-person-page.component.html',
})
export class UpdateDeliveryPersonPageComponent implements OnInit {
  submitted!: boolean;
  deliveryPerson!: DeliveryPerson;
  form!: FormGroup;
  nameControl!: FormControl;

  constructor(
    private service: DeliveryPersonService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.submitted = false;
    this.service.getById(this.route.snapshot.params['id']).subscribe((item) => {
      if (item == null) {
        this.router.navigate(['/']);
      }
      this.deliveryPerson = item;
      this.nameControl = new FormControl(item.name, [Validators.required]);
      this.form = new FormGroup({
        name: this.nameControl,
      });
    });
  }

  onChange(newValue: boolean): void {
    this.deliveryPerson.available = newValue;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.form.value.name != null || this.form.value.name != '') {
      this.deliveryPerson.name = this.form.value.name;
      this.service.update(this.deliveryPerson).subscribe((items) => {
        this.router.navigate(['/']);
      });
    }
  }

  onBack(): void {
    this.router.navigate(['/']);
  }
}
