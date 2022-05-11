import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {  Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CompanyDetailService } from './company-detail.service';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptorService implements HttpInterceptor{

  constructor(private router:Router,private companyDetailService:CompanyDetailService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError(err=>{
      if(err.status===401){
        this.companyDetailService.setLoginId("");
        this.companyDetailService.setlogInStatus(false);
        this.router.navigate(['login'])
      }
    
      const error=err.error.message
    throw new Error(error);
    }))
  }
}
