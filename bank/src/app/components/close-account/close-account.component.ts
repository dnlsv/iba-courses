import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BankService } from 'src/app/services/bank.service';

@Component({
  selector: 'app-close-account',
  templateUrl: './close-account.component.html',
  styleUrls: ['./close-account.component.css']
})
export class CloseAccountComponent implements OnInit {

  number: number = 0;

  constructor(private bankService: BankService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.bankService.closeAccount(this.number).subscribe((data) => {
      console.log(data);
      this.router.navigate(['']);
    }, (error) => alert(error.error));
  }

}
