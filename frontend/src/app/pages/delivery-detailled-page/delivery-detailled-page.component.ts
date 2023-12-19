import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Delivery } from 'src/app/models/delivery.model';
import DeliveryService from 'src/app/services/delivery.service';

@Component({
  templateUrl: './delivery-detailled-page.component.html',
})
export class DeliveryDetailledPageComponent implements OnInit {
  delivery!: Delivery;

  constructor(
    private deliveryService: DeliveryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.deliveryService.getById(id).subscribe({
      next: (item: Delivery) => {
        this.delivery = item;
      },
      error: (err: HttpErrorResponse) => {
        if (err.status != 200) {
          this.router.navigate(['/deliveries']);
        }
      },
    });
  }

  delete(): void {
    this.deliveryService.delete(this.delivery.id).subscribe((r) => {
      this.router.navigate(['/deliveries']);
    });
  }

  onBack(): void {
    history.back();
  }
}
