import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-ping-available',
  templateUrl: './ping-available.component.html',
})
export class PingAvailableComponent implements OnInit {
  ngOnInit(): void {}
  @Input() isAvailable!: boolean;
}
