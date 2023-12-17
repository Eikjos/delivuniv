import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DeliveryPersonService } from 'src/app/services/delivery-person.service';

@Component({
  templateUrl: './insert-delivery-person-page.component.html',
})
export class InsertDeliveryPersonComponent implements OnInit {
  submitted!: boolean;
  name!: string;
  available!: boolean;
  form!: FormGroup;
  nameControl!: FormControl;

  constructor(private service: DeliveryPersonService, private router: Router) {}

  ngOnInit(): void {
    this.submitted = false;
    this.available = true;
    this.nameControl = new FormControl(null, [Validators.required]);
    this.form = new FormGroup({
      name: this.nameControl,
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.name != '' && this.name != null) {
      this.service
        .create(this.form.value.name, this.available)
        .subscribe((item) => {
          this.router.navigate(['/']);
        });
    }
  }

  onChange(event: boolean) {
    this.available = event;
  }

  onBack(): void {
    history.back();
  }
}
