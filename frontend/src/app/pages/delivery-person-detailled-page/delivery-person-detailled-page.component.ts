import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DeliveryPerson } from 'src/app/models/delivery-person.model';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import { DeliveryPersonService } from 'src/app/services/delivery-person.service';

@Component({
  templateUrl: './delivery-person-detailled-page.component.html',
})
export class DeliveryPersonDetailledPageComponent implements OnInit {
  deliveryPerson!: DeliveryPerson;
  deliveryTours!: DeliveryTour[];
  page!: number;
  pageSize!: number;
  date!: Date | null;
  nbPage!: number[];
  itemCount!: number;

  constructor(
    private deliveryPersonService: DeliveryPersonService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.deliveryPersonService.getById(id).subscribe((item) => {
      this.deliveryPerson = item;
      this.pageSize = 10;
      this.page = 0;
      this.deliveryPersonService
        .getDeliveryTours(id, this.page, this.pageSize, null)
        .subscribe((items) => {
          this.deliveryTours = items.data;
          this.itemCount = items.itemCount;
          this.nbPage = Array(items.pageCount)
            .fill(1)
            .map((x, i) => i + 1);
        });
    });
  }

  delete(): void {
    this.deliveryPersonService.delete(this.deliveryPerson.id).subscribe((r) => {
      this.router.navigate(['/']);
    });
  }

  onBack(): void {
    history.back();
  }

  onChangePageSize(): void {
    this.page = 0;
    this.deliveryPersonService
      .getDeliveryTours(
        this.deliveryPerson.id,
        this.page,
        this.pageSize,
        this.date ? new Date(this.date).toISOString() : null
      )
      .subscribe((items) => {
        this.deliveryTours = items.data;
        this.itemCount = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }

  onChangePage(newValue: number): void {
    this.page = newValue;
    this.deliveryPersonService
      .getDeliveryTours(
        this.deliveryPerson.id,
        this.page,
        this.pageSize,
        this.date ? new Date(this.date).toISOString() : null
      )
      .subscribe((items) => {
        this.deliveryTours = items.data;
        this.itemCount = items.itemCount;
        this.nbPage = this.nbPage = Array(items.pageCount)
          .fill(1)
          .map((x, i) => i + 1);
      });
  }
}
