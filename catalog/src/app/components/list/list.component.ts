import { Component, Input, OnInit } from '@angular/core';
import { ElemService } from 'src/app/services/elem.service';
import { Elem } from '../../models/Elem';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css'],
})
export class ListComponent implements OnInit {
  list!: Elem[];

  constructor(private elemServices: ElemService) {}

  ngOnInit(): void {
    this.elemServices.getList().subscribe((list) => {
      this.list = list;
    });
  }

  setClasses() {
    const classes = {
      changeView: this.elemServices.flag,
    };
    return classes;
  }
}
