import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DeliveryPerson } from 'src/app/models/delivery-person.model';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import { Delivery } from 'src/app/models/delivery.model';
import { DeliveryPersonService } from 'src/app/services/delivery-person.service';
import DeliveryTourService from 'src/app/services/delivery-tour.service';
import DeliveryService from 'src/app/services/delivery.service';

@Component({
  templateUrl: './delivery-tour-detailled-page.component.html',
})
export class DeliveryTourDetailledPageComponent implements OnInit {
  deliveryTour!: DeliveryTour;
  page!: number;
  pageSize!: number;
  date!: Date | null;
  deliveries!: Delivery[];
  nbPage!: number[];
  itemCount!: number;

  constructor(
    private deliveryTourService: DeliveryTourService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.deliveryTourService.getById(id).subscribe({
      next: (item: DeliveryTour) => {
        this.deliveryTour = item;
        this.pageSize = 10;
        this.page = 0;
        this.deliveryTourService
          .getDeliveries(id, this.page, this.pageSize)
          .subscribe((items) => {
            this.deliveries = items.data;
            this.itemCount = items.itemCount;
            this.nbPage = Array(items.pageCount)
              .fill(1)
              .map((x, i) => i + 1);
          });
      },
      error: (err: HttpErrorResponse) => {
        if (err.status != 200) {
          this.router.navigate(['delivery-tours']);
        }
      },
    });
  }

  delete(): void {
    this.deliveryTourService.delete(this.deliveryTour.id).subscribe((r) => {
      this.router.navigate(['/delivery-tours']);
    });
  }

  onBack(): void {
    history.back();
  }

  onChangePageSize(): void {
    this.page = 0;
  }

  onChangePage(newValue: number): void {
    this.page = newValue;
  }
}
