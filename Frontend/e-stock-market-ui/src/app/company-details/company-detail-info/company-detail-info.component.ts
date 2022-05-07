import { Component, Input, OnInit } from '@angular/core';
import { CompanyDetailBean } from '../companyDetailBean';

@Component({
  selector: 'app-company-detail-info',
  templateUrl: './company-detail-info.component.html',
  styleUrls: ['./company-detail-info.component.css']
})
export class CompanyDetailInfoComponent implements OnInit {
  @Input()"companyDetailList": CompanyDetailBean;
  constructor() { }

  ngOnInit(): void {
  }

}
