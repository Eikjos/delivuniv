import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DeliveryPersonCardComponent } from './components/delivery-person-card/delivery-person-card.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';

@NgModule({
  declarations: [AppComponent, DeliveryPersonCardComponent, SearchPageComponent],
  imports: [BrowserModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
