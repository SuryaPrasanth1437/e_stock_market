import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import {StockPriceBean} from 'src/app/stocks/stockPriceBean'

@Injectable({
  providedIn: 'root'
})
export class StockPriceService {
  private companyApiUrl = environment.baseUrl;
  constructor(private httpClient: HttpClient) { }

  addStockPrices(stockPriceBean:StockPriceBean,companyCode:String):Observable<any>{
    return this.httpClient.post<void>(this.companyApiUrl+'/stock/add/'+companyCode,stockPriceBean);
  }
}
