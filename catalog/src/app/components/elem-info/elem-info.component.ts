import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Elem } from 'src/app/models/Elem';
import { ElemService } from 'src/app/services/elem.service';

@Component({
  selector: 'app-elem-info',
  templateUrl: './elem-info.component.html',
  styleUrls: ['./elem-info.component.css']
})
export class ElemInfoComponent implements OnInit {

  elem!: Elem;

  constructor(private elemServices: ElemService, private router: Router) { }

  ngOnInit(): void {
    this.elem = this.elemServices.elem;
  }

  onSubmit() {
    this.elemServices.elem = this.elem;
    this.elemServices.updateElem(this.elemServices.elem).subscribe( (data) => 
    console.log(data));
    this.router.navigate(['']);
  }

  onDelete(elem: Elem) : void {
    this.elemServices.deleteElem(elem).subscribe( (data) => 
    console.log(data));  
    this.router.navigate(['']);
  }  

}
