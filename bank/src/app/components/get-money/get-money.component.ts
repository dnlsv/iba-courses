import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Transaction } from 'src/app/models/Transaction';
import { BankService } from 'src/app/services/bank.service';

@Component({
  selector: 'app-get-money',
  templateUrl: './get-money.component.html',
  styleUrls: ['./get-money.component.css'],
})
export class GetMoneyComponent implements OnInit {
  transaction: Transaction = {
    id: 0,
    name: '',
    date: '',
    number: 0,
    amount: 0,
  };

  flag: boolean = true;

  constructor(private bankService: BankService, private router: Router) {}

  ngOnInit(): void {}

  onSubmit() {
    this.bankService
      .changeBalance(
        'get-money',
        this.transaction.number,
        this.transaction.amount
      )
      .subscribe(
        (data) => {
          console.log(data);
          this.bankService
            .addTransaction('get-money', this.transaction)
            .subscribe(
              (data) => {
                console.log(data);
                this.router.navigate(['']);
              },
              (error) => alert(error.error)
            );
        },
        (error) => alert(error.error)
      );
  }
}
