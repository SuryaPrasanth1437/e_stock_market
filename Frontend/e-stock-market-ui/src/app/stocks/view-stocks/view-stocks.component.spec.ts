import { DatePipe } from '@angular/common';
import { HttpClientModule, HttpClient, HttpHandler, HttpErrorResponse, JsonpClientBackend } from '@angular/common/http';
import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule, NgForm, FormGroup, FormControl, Validators, MinLengthValidator } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { min, of, throwError } from 'rxjs';
import { CompanyDetailComponent } from 'src/app/company-details/company-detail/company-detail.component';
import { ViewCompanyDetailComponent } from 'src/app/company-details/view-company-detail/view-company-detail.component';
import { CompanyDetailService } from 'src/app/service/company-detail.service';
import { StockPriceService } from 'src/app/service/stock-price.service';


import { ViewStocksComponent } from './view-stocks.component';
@Component({ selector: 'app-header', template: '' })
class HeaderComponentStub {
}
describe('ViewStocksComponent', () => {
  let component: ViewStocksComponent;
  let fixture: ComponentFixture<ViewStocksComponent>;
  let route: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewStocksComponent, HeaderComponentStub],
      imports: [RouterTestingModule.withRoutes([
        { path: 'view-company-detail/:companyCode', component: ViewCompanyDetailComponent }
      ]), ReactiveFormsModule, FormsModule],
      providers: [HttpClientModule, HttpClient, HttpHandler, NgForm, DatePipe]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewStocksComponent);
    component = fixture.componentInstance;
    component.viewStockPrice = new FormGroup({
      "companyCode": new FormControl('test', [Validators.required]),
      "endDate": new FormControl(new Date(), [Validators.required]),
      "startDate": new FormControl(new Date(), [Validators.required]),
      "min": new FormControl(''),
      "max": new FormControl(''),
      "average": new FormControl('')
    });
    fixture.detectChanges();
  });

  it(`onSearchStock `, () => {
    const app = fixture.componentInstance;
    let stockPriceService = fixture.debugElement.injector.get(StockPriceService);
    spyOn(stockPriceService, "viewPriceDetails").and.callFake(() => {
      return of({
        "min": 1,
        "max": 10,
        "average": 10.0

      })
    })
    const errorResponse = new HttpErrorResponse({
      error: {
        code: 'something went error',
        message: "bad request"
      },
      status: 404,
      statusText: 'bad request'
    })

    let companyDetailService = fixture.debugElement.injector.get(CompanyDetailService);
    spyOn(companyDetailService, "getAllCompanyDetails").and.returnValue(throwError((errorResponse)));
    app.onSearchStock();
    expect(component.viewStockPrice.value.min).toEqual(1)
  });

  it(`companyDetailService 200 status `, () => {
    const app = fixture.componentInstance;
    let stockPriceService = fixture.debugElement.injector.get(StockPriceService);
    spyOn(stockPriceService, "viewPriceDetails").and.callFake(() => {
      return of({
        "min": 1,
        "max": 10,
        "average": 10.0

      })
    })

    const companyDetailLists = [{
      "id": "1",

      "companyCode": "1",

      "companyName": "test",

      "companyCEO": "test",

      "companyTurnOver": "9999",

      "companyWebsite": "abc.com",

      "stockExchange": "1",

      "stockPrice": "1"
    }
    ]

    let companyDetailService = fixture.debugElement.injector.get(CompanyDetailService);
    spyOn(companyDetailService, "getAllCompanyDetails").and.callFake(() => {
      return of(companyDetailLists)
    })

    app.ngOnInit()

    console.log("companyDetails ",app.companyDetailsList)
    expect(app.companyDetailsList).toEqual(companyDetailLists)
  });
})
