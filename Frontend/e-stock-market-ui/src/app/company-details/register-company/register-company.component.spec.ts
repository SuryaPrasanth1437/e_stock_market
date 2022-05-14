import { HttpClientModule, HttpClient, HttpHandler, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { async, ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule, NgForm, FormControl, FormGroup, Validators } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { CompanyDetailService } from 'src/app/service/company-detail.service';
import { CompanyDetailComponent } from '../company-detail/company-detail.component';

import { RegisterCompanyComponent } from './register-company.component';
@Component({ selector: 'app-header', template: '' })
class HeaderComponentStub {
}
describe('RegisterCompanyComponent', () => {
  let component: RegisterCompanyComponent;
  let fixture: ComponentFixture<RegisterCompanyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterCompanyComponent,HeaderComponentStub],
      imports: [RouterTestingModule.withRoutes([
        { path: 'get-all-detail', component: CompanyDetailComponent }
    ]), ReactiveFormsModule, FormsModule],
      providers: [HttpClientModule, HttpClient, HttpHandler, NgForm]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterCompanyComponent);
    component = fixture.componentInstance;
    component.registerCompanyForm=new FormGroup({
      "companyCode": new FormControl(null, [Validators.required]),
      "companyName": new FormControl(null, [Validators.required]),
      "companyCEO": new FormControl(null, [Validators.required]),
      "companyTurnOver": new FormControl(null, [Validators.required, Validators.pattern('^[1-9][0-9]*$')]),
      "companyWebsite": new FormControl(null, [Validators.required]),
      "stockExchange": new FormControl(null, [Validators.required])
    });
    fixture.detectChanges();
  });

  it(`on submitting register form`,fakeAsync(() => {
    const app=fixture.componentInstance;

    let companyDetailService = fixture.debugElement.injector.get(CompanyDetailService);
    spyOn(companyDetailService,"registerCompanyDetail").and.callFake(()=>{
      return of({
        "statusCode":"200"
      })
    })
    app.onSubmitRegisterForm();
    expect(app.isRegister).toBeTrue();
    tick(2001);
    fixture.detectChanges();
    expect(app.isRegister).toBeFalse();
  }));

  it(`on submitting register form failed`,fakeAsync(() => {
    const app=fixture.componentInstance;
    const errorResponse = new HttpErrorResponse({
      error: {
        code: 'something went error',
        message: "bad request"
      },
      status: 404,
      statusText: 'bad request'
    })

    let companyDetailService = fixture.debugElement.injector.get(CompanyDetailService);
    spyOn(companyDetailService,"registerCompanyDetail").and.returnValue(throwError(errorResponse))
    app.onSubmitRegisterForm();
    expect(app.isExist).toBeTrue();
    tick(2001);
    fixture.detectChanges();
    expect(app.isExist).toBeFalse();
  }))

  it(`retrict Zero`,fakeAsync(() => {
    const app=fixture.componentInstance;
    fixture.detectChanges();
    const input=fixture.nativeElement.querySelector('input')
    const event= new KeyboardEvent("keydown",{
      "key":"0",

    })
    input.dispatchEvent(event);
    app.restrictZero(event)
    fixture.detectChanges()
  }))

  
  it(`allow not zero`,fakeAsync(() => {
    const app=fixture.componentInstance;
    fixture.detectChanges();
    const input=fixture.nativeElement.querySelector('input')
    const event= new KeyboardEvent("keydown",{
      "key":"A",

    })
    input.dispatchEvent(event);
    app.restrictZero(event)
    fixture.detectChanges()
  }))

  
 
});
