import { HttpClient, HttpClientModule, HttpErrorResponse, HttpHandler } from '@angular/common/http';
import { Component, NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { NgForm, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { CompanyDetailService } from 'src/app/service/company-detail.service';
import { HeaderComponent } from '../header/header.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { CompanyDetailComponent } from 'src/app/company-details/company-detail/company-detail.component';
@Component({ selector: 'app-header', template: '' })
class HeaderComponentStub {
}
describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let route: Router;

  let mocksTimeout = {
    setTimeout: function () { return 99 }
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent, HeaderComponentStub],
      imports: [RouterTestingModule.withRoutes([
        { path: 'get-all-detail', component: CompanyDetailComponent }
    ]), ReactiveFormsModule, FormsModule],
      providers: [HttpClientModule, HttpClient, HttpHandler, NgForm]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    route = TestBed.get(Router);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  it(`submitting login form`, () => {

    const app = fixture.componentInstance;
    let testForm = new NgForm([], []);
    setTimeout(() => {
      testForm.setValue({
        "username": "test",
        "password": "test"
      }
      )
    });

    let service = fixture.debugElement.injector.get(CompanyDetailService);
    let router = fixture.debugElement.injector.get(Router)
    spyOn(service, "login").and.callFake(() => {
      return of({
        "statusCode": "200",
        "token": "test"
      })
    })



    // spyOn(router,"navigate").and.
    app.onSubmit(testForm)
    expect(app.isLoggedIn).toBeTrue();
  });




  it(`authentication call failed`, () => {

    const app = fixture.componentInstance;
    let testForm = new NgForm([], []);
    setTimeout(() => {
      testForm.setValue({
        "username": "test",
        "password": "test"
      }
      )
    }, 500);

    let service = fixture.debugElement.injector.get(CompanyDetailService);
    let router = fixture.debugElement.injector.get(Router)

    const errorResponse = new HttpErrorResponse({
      error: {
        code: 'something went error',
        message: "bad request"
      },
      status: 404,
      statusText: 'bad request'
    })

    spyOn(service, "login").and.returnValue(throwError(errorResponse))

    // spyOn(router,"navigate").and.
    app.onSubmit(testForm)
    expect(app.isInvalid).toBeTrue();
  });

  it(`timeout function testing`, fakeAsync(() => {

    const app = fixture.componentInstance;
    let testForm = new NgForm([], []);
    testForm.form.patchValue({
      "username": "test",
      "password": "test"
    })

    let service = fixture.debugElement.injector.get(CompanyDetailService);
    let router = fixture.debugElement.injector.get(Router)
    spyOn(service, "login").and.callFake(() => {
      return of({
        "statusCode": "200",
        "token": "test"
      })
    })


    // spyOn(router,"navigate").and.
    app.onSubmit(testForm)
    tick(2001);
    fixture.detectChanges()
    expect(app.isLoggedIn).toBeFalse()
  }));

});
