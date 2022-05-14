import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { StockPriceBean } from 'src/app/stocks/stockPriceBean';
import { CompanyDetailService } from './company-detail.service';
import { StockPriceService } from './stock-price.service';

describe('StockPriceService', () => {
  let service: StockPriceService;

  beforeEach(() => {
    const companyDetailServiceStub = () => ({ getToken: () => ({}) });
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        StockPriceService,
        { provide: CompanyDetailService, useFactory: companyDetailServiceStub }
      ]
    });
    service = TestBed.inject(StockPriceService);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });
});
