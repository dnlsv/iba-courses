import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../models/Account';
import { Transaction } from '../models/Transaction';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'aplication-json'
  })
}


@Injectable({
  providedIn: 'root'
})
export class BankService {

  todoUrl = 'http://localhost:45555';

  constructor(private http: HttpClient) { }

  openAccount(account: Account) : Observable<Account> {
    const newAccount: Account = {
      number: account.number,
      name: account.name,
      currency: account.currency,
      balance: account.balance
    }
    return this.http.post<Account>(
      `${this.todoUrl}/open-account`, newAccount, httpOptions
    )
  }

  closeAccount(number: number){
    return this.http.delete<Account>(`${this.todoUrl}/close-account/?number=${number}`, httpOptions);
  }

  changeBalance(path: String, number: number, amount: number) {
    return this.http.put<Account>(`${this.todoUrl}/${path}/?number=${number}&amount=${amount}`, httpOptions);
  }

  addTransaction(path: String, transaction: Transaction) : Observable<Transaction> {
    const newTransaction: Transaction = {
      id: transaction.number,
      name: transaction.name,
      date: transaction.date,
      number: transaction.number,
      amount: transaction.amount
    }
    return this.http.post<Transaction>(
      `${this.todoUrl}/${path}`, newTransaction, httpOptions
    )
  }

  getAccount(number: number) : Observable<Account> {
    return this.http.get<Account>(
      `${this.todoUrl}/get-account/?number=${number}`
    );
  }

  getTransactions(number: number) : Observable<Transaction[]> {
    return this.http.get<Transaction[]>(
      `${this.todoUrl}/get-transactions/?number=${number}`
    );
  }
}
