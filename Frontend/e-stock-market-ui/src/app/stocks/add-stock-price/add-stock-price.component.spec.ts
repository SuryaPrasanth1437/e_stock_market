import { HttpClientModule, HttpClient, HttpHandler, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule, NgForm, FormControl, FormGroup, Validators } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { CompanyDetailComponent } from 'src/app/company-details/company-detail/company-detail.component';
import { StockPriceService } from 'src/app/service/stock-price.service';

import { AddStockPriceComponent } from './add-stock-price.component';
@Component({ selector: 'app-header', template: '' })
class HeaderComponentStub {
}
describe('AddStockPriceComponent', () => {
  let component: AddStockPriceComponent;
  let fixture: ComponentFixture<AddStockPriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddStockPriceComponent,HeaderComponentStub ],
      imports: [RouterTestingModule.withRoutes([
        { path: 'get-all-detail', component: CompanyDetailComponent }
    ]), ReactiveFormsModule, FormsModule],
      providers: [HttpClientModule, HttpClient, HttpHandler, NgForm]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStockPriceComponent);
    component = fixture.componentInstance;
    component.addStockPriceForm== new FormGroup({
      "companyCode": new FormControl(null, [Validators.required]),
      "stckPrice": new FormControl(null, [Validators.required])
    })
    fixture.detectChanges();
  });

  it(`add stock price`,fakeAsync(()=>{
    const app = fixture.componentInstance;
    let stockPriceService=fixture.debugElement.injector.get(StockPriceService);
    spyOn(stockPriceService,"addStockPrices").and.callFake(()=>{
      return of ({
        "statusCode":"200"
      })
    })
    app.addStockPrice()
    tick(2001);
    fixture.detectChanges()
    expect(app.stockAdd).toBeFalse()
  }))

  it(`add stock price failed`,fakeAsync(()=>{
    const app = fixture.componentInstance;
    let stockPriceService=fixture.debugElement.injector.get(StockPriceService);
    const errorResponse = new HttpErrorResponse({
      error: {
        code: 'something went error',
        message: "bad request"
      },
      status: 404,
      statusText: 'bad request'
    })
    spyOn(stockPriceService,"addStockPrices").and.returnValue(throwError(errorResponse))
    app.addStockPrice()
    tick(2001);
    fixture.detectChanges()
    expect(app.stockFailAdd).toBeFalse()
  }))
  
});
