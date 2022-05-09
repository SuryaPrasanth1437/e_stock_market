import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { ThemePalette } from '@angular/material/core';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-view-stocks',
  templateUrl: './view-stocks.component.html',
  styleUrls: ['./view-stocks.component.css']
})
export class ViewStocksComponent implements OnInit {
  "viewStockPrice": FormGroup
  @ViewChild('picker') picker: any;

  public "date": moment.Moment;
  public "disabled" = true;
  public "showSpinners" = true;
  public "showSeconds" = false;
  public "touchUi" = true;
  public "enableMeridian" = true;
  public "minDate": moment.Moment;
  public "maxDate"= new Date();
  public "stepHour" = 1;
  public "stepMinute" = 1;
  public "stepSecond" = 1;
  public "disableMinute"=false;
public "hideTime"=false;
  public color: ThemePalette = 'primary';
  
  constructor(private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.viewStockPrice = new FormGroup({
      "search": new FormControl(null, [Validators.required]),
      "endDate": new FormControl(null, [Validators.required]),
      "startDate":  new FormControl(null, [Validators.required])
    });
    
  }

  onSearchStock(){
    console.log(this.viewStockPrice.value.endDate.toISOString())
  }

 

}
