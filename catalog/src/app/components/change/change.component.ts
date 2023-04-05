import { Component, OnInit } from '@angular/core';
import { ElemService } from 'src/app/services/elem.service';

@Component({
  selector: 'app-change',
  templateUrl: './change.component.html',
  styleUrls: ['./change.component.css'],
})
export class ChangeComponent implements OnInit {
  constructor(private elemServices: ElemService) {}

  ngOnInit(): void {}

  onClick() {
    this.elemServices.flag = !this.elemServices.flag;
  }
}
