import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DeliveryPerson } from 'src/app/models/delivery-person.model';
import { DeliveryPersonService } from 'src/app/services/delivery-person.service';

@Component({
  selector: 'app-delivery-person-detailled-page',
  templateUrl: './delivery-person-detailled-page.component.html',
})
export class DeliveryPersonDetailledPageComponent implements OnInit {
  deliveryPerson!: DeliveryPerson;

  constructor(
    private service: DeliveryPersonService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.service.getById(id).subscribe((item) => {
      this.deliveryPerson = item;
    });
  }

  delete(): void {
    this.service.delete(this.deliveryPerson.id).subscribe((r) => {
      this.router.navigate(['/']);
    });
  }

  onBack(): void {
    history.back();
  }
}
