import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from 'src/app/models/Account';
import { BankService } from 'src/app/services/bank.service';

@Component({
  selector: 'app-open-account',
  templateUrl: './open-account.component.html',
  styleUrls: ['./open-account.component.css'],
})
export class OpenAccountComponent implements OnInit {
  account: Account = {
    number: 0,
    name: '',
    currency: '',
    balance: 0,
  };

  constructor(private bankService: BankService, private router: Router) {}

  ngOnInit(): void {}

  onSubmit() {
    this.bankService.openAccount(this.account).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(['']);
      },
      (error) => alert(error.error)
    );
  }
}
