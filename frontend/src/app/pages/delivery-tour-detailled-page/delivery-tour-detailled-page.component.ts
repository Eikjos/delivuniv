import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DeliveryPerson } from 'src/app/models/delivery-person.model';
import { DeliveryTour } from 'src/app/models/delivery-tour.model';
import { DeliveryPersonService } from 'src/app/services/delivery-person.service';
import DeliveryTourService from 'src/app/services/delivery-tour.service';

@Component({
  templateUrl: './delivery-tour-detailled-page.component.html',
})
export class DeliveryTourDetailledPageComponent implements OnInit {
  deliveryTour!: DeliveryTour;
  page!: number;
  pageSize!: number;
  date!: Date | null;
  nbPage!: number[];

  constructor(
    private deliveryTourService: DeliveryTourService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.deliveryTourService.getById(id).subscribe((item) => {
      this.deliveryTour = item;
      this.pageSize = 10;
      this.page = 0;
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
