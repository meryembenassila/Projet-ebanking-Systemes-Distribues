
import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AsyncPipe } from '@angular/common';
import { Account, AccountList, RequestStatus } from '../../model/account.model';
import {catchError, map, Observable, of} from 'rxjs';
import { Loading } from "../services/loading";

@Component({
  selector: 'app-accounts',
  imports: [AsyncPipe],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})
export class Accounts {
  private http = inject(HttpClient);
  public loadingService = inject(Loading);

  accounts$:Observable<AccountList> = this.http
    .get<Account[]>('http://localhost:8888/EBANK-SERVICE/BankAccounts')
    .pipe(
      map(res => ({
        accounts: res,
        status: RequestStatus.SUCCESS
      })),
      catchError(err =>
        of({
          status: RequestStatus.ERROR,
          errorMessage: err.statusText
        })
      )
    );
  protected readonly RequestStatus = RequestStatus;
}

