import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { observable, Observable, Observer } from 'rxjs';
import { CompanyDetailService } from './company-detail.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardGuard implements CanActivate {
  constructor(private companyDetailService: CompanyDetailService, private router: Router) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const isLoggedIn= this.companyDetailService.getLogInStatus();
    if (isLoggedIn) {
      return true;
    }else{
      this.router.navigate(['login'])
      return false;
    }
  }

  
}
