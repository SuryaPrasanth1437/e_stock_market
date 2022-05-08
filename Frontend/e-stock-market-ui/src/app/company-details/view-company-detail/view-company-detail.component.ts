import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { CompanyDetailService } from 'src/app/service/company-detail.service';

@Component({
  selector: 'app-view-company-detail',
  templateUrl: './view-company-detail.component.html',
  styleUrls: ['./view-company-detail.component.css']
})
export class ViewCompanyDetailComponent implements OnInit {
  "viewCompanyForm": FormGroup;
  constructor(private route: ActivatedRoute, private companyDetailService: CompanyDetailService) { }

  ngOnInit(): void {
    this.viewCompanyForm = new FormGroup({
      "companyCode": new FormControl(null, [Validators.required]),
      "companyName": new FormControl(null, [Validators.required]),
      "companyCEO": new FormControl(null, [Validators.required]),
      "companyTurnOver": new FormControl(null, [Validators.required, Validators.pattern('^[1-9][0-9]*$')]),
      "companyWebsite": new FormControl(null, [Validators.required]),
      "stockExchange": new FormControl(null, [Validators.required]),
      "stockPrice": new FormControl(null, [Validators.required])
    });
    this.route.params.subscribe({
      next: (params) => {
        const companyCode = params['companyCode'];
        this.companyDetailService.viewCompanyDetails(companyCode).subscribe({
          next: (data: any) => {
            this.viewCompanyForm.patchValue({
              "companyCode": data.companyCode,
              "companyName": data.companyName,
              "companyCEO": data.companyCEO,
              "companyTurnOver": data.companyTurnOver,
              "companyWebsite": data.companyWebsite,
              "stockExchange": data.stockExchange,
              "stockPrice": data.stockPrice
            })
          },
          error: (error: any) => {
            console.log("error" + JSON.stringify(error));
          }
        });
      }
    })
  }
}
