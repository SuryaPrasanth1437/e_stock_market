import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../User';
import { HttpClient } from '@angular/common/http';
import { CompanyDetailBean } from '../company-details/companyDetailBean';

@Injectable({
  providedIn: 'root'
})
export class CompanyDetailService {
  private companyApiUrl = environment.baseUrl;
  "isLoggedIn": boolean;
  "loginId": string;

  constructor(private httpClient: HttpClient) { }
  getLogInStatus() {
    return this.isLoggedIn
  }
  setlogInStatus(login: boolean) {
    this.isLoggedIn = login;
  }

  getLoginId() {
    return this.loginId;
  }
  setLoginId(loginId: string) {
    this.loginId = loginId;
  }
  login(user: User): Observable<any> {
    return this.httpClient.post<void>(this.companyApiUrl + '/authentication/authenticate', user);
  }

  getAllCompanyDetails(): Observable<CompanyDetailBean[]> {
    return this.httpClient.get<CompanyDetailBean[]>(this.companyApiUrl  + "/company/getall");
  }
}
