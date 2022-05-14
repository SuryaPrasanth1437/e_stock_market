import { HttpClientModule, HttpClient, HttpHandler } from '@angular/common/http';
import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule, NgForm } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { CompanyDetailComponent } from 'src/app/company-details/company-detail/company-detail.component';
import { CompanyDetailService } from 'src/app/service/company-detail.service';

import { HeaderComponent } from './header.component';
@Component({ selector: 'app-header', template: '' })
class HeaderComponentStub {
}
describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderComponent ,HeaderComponentStub],
      imports: [RouterTestingModule.withRoutes([
        { path: 'get-all-detail', component: CompanyDetailComponent }
    ]), ReactiveFormsModule, FormsModule],
      providers: [HttpClientModule, HttpClient, HttpHandler, NgForm]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  
  it(`get login status`,()=>{
    const app =fixture.componentInstance;

    let companyDetailService=fixture.debugElement.injector.get(CompanyDetailService);
    spyOn(companyDetailService,"getLogInStatus").and.returnValue(true);
    expect(app.isLoggedIn()).toBeTrue()
  })
 
});
