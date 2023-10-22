import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-toggle-button',
  templateUrl: './toggle-button.component.html',
})
export class ToggleButtonComponent implements OnInit {
  ngOnInit(): void {}
  @Input() available!: boolean;
  @Output() onChangeParent = new EventEmitter();

  onChange(): void {
    this.available = !this.available;
    this.onChangeCheckbox();
  }

  onChangeCheckbox(): void {
    this.onChangeParent.emit(this.available);
  }
}
