import { DatePipe } from '@angular/common';
import { HttpClientModule, HttpClient, HttpHandler } from '@angular/common/http';
import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule, NgForm, FormControl, FormGroup, Validators } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { CompanyDetailService } from 'src/app/service/company-detail.service';

import { ViewCompanyDetailComponent } from './view-company-detail.component';

@Component({ selector: 'app-header', template: '' })
class HeaderComponentStub {
}
describe('ViewCompanyDetailComponent', () => {
  let component: ViewCompanyDetailComponent;
  let fixture: ComponentFixture<ViewCompanyDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewCompanyDetailComponent , HeaderComponentStub],
      imports: [RouterTestingModule.withRoutes([
        { path: 'view-company-detail/:companyCode', component: ViewCompanyDetailComponent }
      ]), ReactiveFormsModule, FormsModule],
      providers: [HttpClientModule, HttpClient, HttpHandler, NgForm, DatePipe]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCompanyDetailComponent);
    component = fixture.componentInstance;
    component.viewCompanyForm== new FormGroup({
      "companyCode": new FormControl(null, [Validators.required]),
      "companyName": new FormControl(null, [Validators.required]),
      "companyCEO": new FormControl(null, [Validators.required]),
      "companyTurnOver": new FormControl(null, [Validators.required, Validators.pattern('^[1-9][0-9]*$')]),
      "companyWebsite": new FormControl(null, [Validators.required]),
      "stockExchange": new FormControl(null, [Validators.required]),
      "stockPrice": new FormControl(null, [Validators.required])
    });
    fixture.detectChanges();
  });


  it(`view comapny detail`,()=>{
    const app=fixture.componentInstance;
    const companyDetailService=fixture.debugElement.injector.get(CompanyDetailService)

    const companyDetailLists = {
      "id": "1",

      "companyCode": "1",

      "companyName": "test",

      "companyCEO": "test",

      "companyTurnOver": "9999",

      "companyWebsite": "abc.com",

      "stockExchange": "1",

      "stockPrice": "1"
    }
    
    spyOn(companyDetailService,"viewCompanyDetails").and.callFake(()=>{
      return of(companyDetailLists)
    });
    app.ngOnInit()
    expect(app.viewCompanyForm.value.companyCode).toEqual(companyDetailLists.companyCode)
  })
});
