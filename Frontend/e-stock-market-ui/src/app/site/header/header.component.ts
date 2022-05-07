import { Component, OnInit } from '@angular/core';
import { CompanyDetailService } from 'src/app/service/company-detail.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private companyDetailService:CompanyDetailService) { }

  ngOnInit(): void {
  }

  isLoggedIn(){
    return this.companyDetailService.getLogInStatus();
  }
}
