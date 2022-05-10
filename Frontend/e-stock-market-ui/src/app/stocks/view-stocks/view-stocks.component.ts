import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { ThemePalette } from '@angular/material/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { StockPriceService } from 'src/app/service/stock-price.service';
import { CompanyDetailService } from 'src/app/service/company-detail.service';
import { CompanyDetailBean } from 'src/app/company-details/companyDetailBean';
@Component({
  selector: 'app-view-stocks',
  templateUrl: './view-stocks.component.html',
  styleUrls: ['./view-stocks.component.css']
})
export class ViewStocksComponent implements OnInit {
  "viewStockPrice": FormGroup
  @ViewChild('picker') picker: any;
  "startDateFinal":any;
  "endDateFinal":any;
  "submit":boolean;
  "companyDetailsList": CompanyDetailBean[];

  public "date": moment.Moment;
  public "disabled" = true;
  public "showSpinners" = true;
  public "showSeconds" = false;
  public "touchUi" = true;
  public "enableMeridian" = true;
  public "minDate": moment.Moment;
  public "maxDate" = new Date();
  public "stepHour" = 1;
  public "stepMinute" = 1;
  public "stepSecond" = 1;
  public "disableMinute" = false;
  public "hideTime" = false;
  public color: ThemePalette = 'primary';


  constructor(private companyDetailService:CompanyDetailService,private datePipe: DatePipe, private stockPriceService: StockPriceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.companyDetailService.getAllCompanyDetails().subscribe({
      next: (data: any) => {
        this.companyDetailsList = data;
      },
      error: (error: any) => {
        console.log("error" + JSON.stringify(error));
      }

    });
    this.viewStockPrice = new FormGroup({
      "companyCode": new FormControl(null, [Validators.required]),
      "endDate": new FormControl(null, [Validators.required]),
      "startDate": new FormControl(null, [Validators.required]),
      "min": new FormControl(null ),
      "max": new FormControl(null),
      "average": new FormControl(null)
    });

  }

  onSearchStock() {

    const startDate = this.datePipe.transform(this.viewStockPrice.value.startDate, "yyyy-MM-dd'T'HH:mm:ss");
    const endDate = this.datePipe.transform(this.viewStockPrice.value.endDate, "yyyy-MM-dd'T'HH:mm:ss");
    console.log("start Date ", startDate);
    console.log("End date ", endDate);
    this.startDateFinal=this.datePipe.transform(this.viewStockPrice.value.startDate, "dd/MM/yyyy")
    this.endDateFinal=this.datePipe.transform(this.viewStockPrice.value.endDate, "dd/MM/yyyy")
    this.stockPriceService.viewPriceDetails(this.viewStockPrice.value.companyCode, startDate, endDate).subscribe({
      next: (data) => {
        this.submit=true;
        this.viewStockPrice.patchValue({
          "min":data.min,
          "max":data.max,
          "average":data.average
        })
      }
    })
  }



}
