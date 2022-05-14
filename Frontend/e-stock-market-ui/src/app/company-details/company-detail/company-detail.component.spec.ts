import { DatePipe } from '@angular/common';
import { HttpClientModule, HttpClient, HttpHandler, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule, NgForm } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { CompanyDetailService } from 'src/app/service/company-detail.service';
import { StockPriceService } from 'src/app/service/stock-price.service';
import { ViewCompanyDetailComponent } from '../view-company-detail/view-company-detail.component';

import { CompanyDetailComponent } from './company-detail.component';

@Component({ selector: 'app-header', template: '' })
class HeaderComponentStub {
}
describe('CompanyDetailComponent', () => {
  let component: CompanyDetailComponent;
  let fixture: ComponentFixture<CompanyDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompanyDetailComponent, HeaderComponentStub],
      imports: [RouterTestingModule.withRoutes([
        { path: 'view-company-detail/:companyCode', component: ViewCompanyDetailComponent }
      ]), ReactiveFormsModule, FormsModule],
      providers: [HttpClientModule, HttpClient, HttpHandler, NgForm, DatePipe]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it(`companyDetailService 200 status `, () => {
    const app = fixture.componentInstance;
    
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
    expect(app.companyDetailList).toEqual(companyDetailLists)
    
  });

  it(`delete company code `, () => {
    const app = fixture.componentInstance;
    
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

    spyOn(companyDetailService,"deleteCompanyDetail").and.callFake(()=>{
      return of({
        "statusText":"good",
        "statusCode": "200"
      })
    })

    app.deleteComapny("1");
    expect(app.isRemoved).toBeTrue()
  });
 

  it(`delete company code throws error `,fakeAsync(() => {
    const app = fixture.componentInstance;
    
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


    spyOn(companyDetailService,"deleteCompanyDetail").and.callFake(()=>{
      return of({
        "statusText":"good",
        "statusCode": "200"
      })
    })
    app.deleteComapny("1");
    tick(2100);
    fixture.detectChanges()
    expect(app.isRemoved).toBeFalse()
  }));
});