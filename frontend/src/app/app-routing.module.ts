import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DeliveryPersonDetailledPageComponent } from 'src/app/pages/delivery-person-detailled-page/delivery-person-detailled-page.component';
import { DeliveryTourDetailledPageComponent } from 'src/app/pages/delivery-tour-detailled-page/delivery-tour-detailled-page.component';
import { InsertDeliveryPersonComponent } from 'src/app/pages/insert-delivery-person-page/insert-delivery-person-page.component';
import { InsertDeliveryTourComponent } from 'src/app/pages/insert-delivery-tour-page/insert-delivery-tour-page.component';
import { SearchDeliveryPersonPageComponent } from 'src/app/pages/search-delivery-person-page/search-delivery-person-page.component';
import { SearchDeliveryTourPageComponent } from 'src/app/pages/search-delivery-tours-page/search-delivery-tour-page.component';
import { UpdateDeliveryPersonPageComponent } from 'src/app/pages/update-delivery-person-page/update-delivery-person-page.component';
import { UpdateDeliveryTourComponent } from 'src/app/pages/update-delivery-tour-page/update-delivery-tour-page.component';

const routes: Routes = [
  {
    path: '',
    component: SearchDeliveryPersonPageComponent,
  },
  {
    path: 'delivery-persons/create',
    component: InsertDeliveryPersonComponent,
  },
  {
    path: 'delivery-persons/:id',
    component: DeliveryPersonDetailledPageComponent,
  },
  {
    path: 'delivery-persons/:id/update',
    component: UpdateDeliveryPersonPageComponent,
  },
  {
    path: 'delivery-tours',
    component: SearchDeliveryTourPageComponent,
  },
  {
    path: 'delivery-tours/create',
    component: InsertDeliveryTourComponent,
  },
  {
    path: 'delivery-tours/:id',
    component: DeliveryTourDetailledPageComponent,
  },
  {
    path: 'delivery-tours/:id/update',
    component: UpdateDeliveryTourComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
