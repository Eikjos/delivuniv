import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import DeliveryPersonCardComponent from './components/delivery-person-card/delivery-person-card.component';
import DeliveryTourCardComponent from './components/delivery-tour-card/delivery-tour-card.component';
import { SearchDeliveryPersonPageComponent } from './pages/search-delivery-person-page/search-delivery-person-page.component';
import { SearchDeliveryTourPageComponent } from 'src/app/pages/search-delivery-tours-page/search-delivery-tour-page.component';
import { environment } from 'src/environments/environment';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { baseUrlInterceptor } from 'src/baseUrlInterceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from 'src/app/components/header/header.component';
import { PingAvailableComponent } from 'src/app/components/ping-available/ping-available.component';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { DeliveryPersonDetailledPageComponent } from 'src/app/pages/delivery-person-detailled-page/delivery-person-detailled-page.component';
import { UpdateDeliveryPersonPageComponent } from 'src/app/pages/update-delivery-person-page/update-delivery-person-page.component';
import { ToggleButtonComponent } from 'src/app/components/toggle-button/toggle-button.component';
import { InsertDeliveryPersonComponent } from 'src/app/pages/insert-delivery-person-page/insert-delivery-person-page.component';
import { LoadingComponent } from 'src/app/components/loading/loading.component';
import { InsertDeliveryTourComponent } from 'src/app/pages/insert-delivery-tour-page/insert-delivery-tour-page.component';
import { DeliveryTourDetailledPageComponent } from 'src/app/pages/delivery-tour-detailled-page/delivery-tour-detailled-page.component';
import { UpdateDeliveryTourComponent } from 'src/app/pages/update-delivery-tour-page/update-delivery-tour-page.component';
import DeliveryCardComponent from 'src/app/components/delivery-card/delivery-card.component';
import { SearchDeliveryPageComponent } from 'src/app/pages/search-delivery-page/search-delivery-page.component';
import { InsertDeliveryComponent } from 'src/app/pages/insert-delivery-page/insert-delivery-page.component';
import { DeliveryDetailledPageComponent } from 'src/app/pages/delivery-detailled-page/delivery-detailled-page.component';
import { UpdateDeliveryComponent } from 'src/app/pages/update-delivery-page/update-delivery-page.component';

registerLocaleData(localeFr, 'fr');
@NgModule({
  declarations: [
    AppComponent,
    DeliveryPersonCardComponent,
    DeliveryCardComponent,
    PingAvailableComponent,
    ToggleButtonComponent,
    SearchDeliveryPersonPageComponent,
    SearchDeliveryTourPageComponent,
    SearchDeliveryPageComponent,
    HeaderComponent,
    DeliveryPersonDetailledPageComponent,
    DeliveryDetailledPageComponent,
    DeliveryTourDetailledPageComponent,
    UpdateDeliveryPersonPageComponent,
    UpdateDeliveryTourComponent,
    UpdateDeliveryComponent,
    InsertDeliveryPersonComponent,
    InsertDeliveryTourComponent,
    InsertDeliveryComponent,
    LoadingComponent,
    DeliveryTourCardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: baseUrlInterceptor,
      multi: true,
    },
    { provide: 'base_api_url', useValue: environment.apiUrl, deps: [] },
    { provide: LOCALE_ID, useValue: 'fr-FR' },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
