import {Accounts} from '../app/accounts/accounts';

export  interface Account{
  id? :string,
  balance? :number;
  CreatedAt? :string,
  type? :string,
  idclient? :number
}

export enum RequestStatus {
  SUCCESS,ERROR
}

export interface AccountList {
  accounts? :Account[],
  status? : RequestStatus,
  errorMessage? :string
}
