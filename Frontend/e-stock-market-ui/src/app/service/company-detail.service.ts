import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../User';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CompanyDetailBean } from '../company-details/companyDetailBean';

@Injectable({
  providedIn: 'root'
})
export class CompanyDetailService {
  private companyApiUrl = environment.baseUrl;
  "isLoggedIn": boolean;
  "loginId": string;
  "token":string;

  constructor(private httpClient: HttpClient) { }
  getLogInStatus() {
    return this.isLoggedIn
  }
  setlogInStatus(login: boolean) {
    this.isLoggedIn = login;
  }

  setToken(token: string){
    this.token=token;
  }

  getToken(){
    return this.token;
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
    
    const httpOptions={
      headers: new HttpHeaders({
        "Content-Type":"application/json",
        "Authorization": "Bearer " + this.getToken()
      })
    }
    return this.httpClient.get<CompanyDetailBean[]>(this.companyApiUrl + "/company/getall",httpOptions);
  }

  registerCompanyDetail(CompanyDetailBean: CompanyDetailBean): Observable<any> {
    const httpOptions={
      headers: new HttpHeaders({
        "Content-Type":"application/json",
        "Authorization": "Bearer " + this.getToken()
      })
    }
    return this.httpClient.post<void>(this.companyApiUrl + "/company/register", CompanyDetailBean,httpOptions);
  }

  viewCompanyDetails(companyCode: String): Observable<CompanyDetailBean> {
    const httpOptions={
      headers: new HttpHeaders({
        "Content-Type":"application/json",
        "Authorization": "Bearer " + this.getToken()
      })
    }
    return this.httpClient.get<CompanyDetailBean>(this.companyApiUrl + "/company/info/" + companyCode,httpOptions);
  }

  deleteCompanyDetail(companyCode: String): Observable<any> {
    const httpOptions={
      headers: new HttpHeaders({
        "Content-Type":"application/json",
        "Authorization": "Bearer " + this.getToken()
      })
    }
    return this.httpClient.delete<void>(this.companyApiUrl + "/company/delete/"+companyCode,httpOptions);
  }
}
