import { Component, OnInit, Input } from '@angular/core';
import { Elem } from 'src/app/models/Elem';
import { ElemService } from 'src/app/services/elem.service';

@Component({
  selector: 'app-elem-item',
  templateUrl: './elem-item.component.html',
  styleUrls: ['./elem-item.component.css']
})
export class ElemItemComponent implements OnInit {

  @Input() elem!: Elem;

  constructor(private elemServices: ElemService) { }

  ngOnInit(): void {
  }

  onClick(elem: Elem)
  {
    this.elemServices.elem = elem;
  }

}
