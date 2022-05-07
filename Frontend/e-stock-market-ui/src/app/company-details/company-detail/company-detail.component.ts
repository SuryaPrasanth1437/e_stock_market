import { Component, OnInit } from '@angular/core';
import { CompanyDetailService } from 'src/app/service/company-detail.service';
import { CompanyDetailBean } from '../companyDetailBean';
@Component({
  selector: 'app-company-detail',
  templateUrl: './company-detail.component.html',
  styleUrls: ['./company-detail.component.css']
})
export class CompanyDetailComponent implements OnInit {
  "companyDetails": CompanyDetailBean[];
  "companyDetailList": CompanyDetailBean[]
  constructor(private companyDetailService: CompanyDetailService) { }

  ngOnInit(): void {
    this.companyDetailService.getAllCompanyDetails().subscribe({
      next: (data: any) => {
        this.companyDetails = data;
        this.companyDetailList = data
      },
      error: (error: any) => {
        console.log("error" + JSON.stringify(error));
      }

    });
  }

}
