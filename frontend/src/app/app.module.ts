import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DeliveryPersonCardComponent } from './components/delivery-person-card/delivery-person-card.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { environment } from 'src/environment';
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
// import { InputMaskModule } from 'primeng/inputmask';

registerLocaleData(localeFr, 'fr');
@NgModule({
  declarations: [
    AppComponent,
    DeliveryPersonCardComponent,
    PingAvailableComponent,
    ToggleButtonComponent,
    SearchPageComponent,
    HeaderComponent,
    DeliveryPersonDetailledPageComponent,
    UpdateDeliveryPersonPageComponent,
    InsertDeliveryPersonComponent,
    LoadingComponent,
    //InputMaskModule,
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
