import { Component, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CompanyDetailService } from 'src/app/service/company-detail.service';
import { EventEmitter } from '@angular/core';;
import { CompanyDetailBean } from '../companyDetailBean';

@Component({
  selector: 'app-company-detail-info',
  templateUrl: './company-detail-info.component.html',
  styleUrls: ['./company-detail-info.component.css']
})
export class CompanyDetailInfoComponent implements OnInit {
  @Input() "companyDetailList": CompanyDetailBean;
  @Output() delete: EventEmitter<any> = new EventEmitter();

  "isRemoved": boolean;
  constructor(private companyDetailService: CompanyDetailService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }
  
  deleteCompany(companyCode: any) {
    this.delete.emit(companyCode);
  }


}
