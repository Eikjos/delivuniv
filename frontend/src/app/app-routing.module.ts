import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DeliveryPersonDetailledPageComponent } from 'src/app/pages/delivery-person-detailled-page/delivery-person-detailled-page.component';
import { InsertDeliveryPersonComponent } from 'src/app/pages/insert-delivery-person-page/insert-delivery-person-page.component';
import { SearchPageComponent } from 'src/app/pages/search-page/search-page.component';
import { UpdateDeliveryPersonPageComponent } from 'src/app/pages/update-delivery-person-page/update-delivery-person-page.component';

const routes: Routes = [
  {
    path: '',
    component: SearchPageComponent,
  },
  {
    path: 'delivery-person/create',
    component: InsertDeliveryPersonComponent,
  },
  {
    path: 'delivery-person/:id',
    component: DeliveryPersonDetailledPageComponent,
  },
  {
    path: 'delivery-person/:id/update',
    component: UpdateDeliveryPersonPageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
