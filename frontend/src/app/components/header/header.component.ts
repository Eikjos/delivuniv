import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {
  @Input() page!: 'delivery' | 'delivery-tour' | 'delivery-person';
}
