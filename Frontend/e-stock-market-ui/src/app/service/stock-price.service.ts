import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ObservableNotification } from 'rxjs';
import { environment } from 'src/environments/environment';
import { StockPriceBean } from 'src/app/stocks/stockPriceBean'
import { ViewPriceDetail } from '../stocks/view-price-details';
import { CompanyDetailService } from './company-detail.service';

@Injectable({
  providedIn: 'root'
})
export class StockPriceService {
  private companyApiUrl = environment.baseUrl;
  constructor(private httpClient: HttpClient,private companyDetailService:CompanyDetailService) { }

  addStockPrices(stockPriceBean: StockPriceBean, companyCode: String): Observable<any> {
    const httpOptions={
      headers: new HttpHeaders({
        "Content-Type":"application/json",
        "Authorization": "Bearer " + this.companyDetailService.getToken()
      })
    }
    return this.httpClient.post<void>(this.companyApiUrl + '/stock/add/' + companyCode, stockPriceBean,httpOptions);
  }

  viewPriceDetails(companyCode: String, startDate: any, endDate: any): Observable<any> {
    const httpOptions={
      headers: new HttpHeaders({
        "Content-Type":"application/json",
        "Authorization": "Bearer " + this.companyDetailService.getToken()
      })
    }
    return this.httpClient.get<ViewPriceDetail>(this.companyApiUrl + "/stock/get/" + companyCode + "/" + startDate + "/" + endDate,httpOptions);
  }
}
