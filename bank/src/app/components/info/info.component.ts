import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/Account';
import { Transaction } from 'src/app/models/Transaction';
import { BankService } from 'src/app/services/bank.service';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  account: Account = {
    number: 0,
    name: '',
    currency: '',
    balance: 0
  };
  number: number = 0;

  transactions: Transaction[] = [];

  constructor(private bankService: BankService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.account.number = 0;
    this.account.name = "";
    this.account.currency = "";
    this.account.balance = 0;
    this.transactions = [];
    this.bankService.getAccount(this.number).subscribe(data => {
      console.log(data);
      this.account = data;
      this.bankService.getTransactions(this.number).subscribe(data => {
        console.log(data);
        this.transactions = data;
      }, (error) => alert(error.error));
    }, (error) => alert(error.error));
  }
}
