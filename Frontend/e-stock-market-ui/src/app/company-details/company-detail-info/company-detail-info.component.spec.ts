import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { CompanyDetailService } from 'src/app/service/company-detail.service';
import { CompanyDetailInfoComponent } from './company-detail-info.component';

describe('CompanyDetailInfoComponent', () => {
  let component: CompanyDetailInfoComponent;
  let fixture: ComponentFixture<CompanyDetailInfoComponent>;

  beforeEach(() => {
    const activatedRouteStub = () => ({});
    const routerStub = () => ({});
    const companyDetailServiceStub = () => ({});
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [CompanyDetailInfoComponent],
      providers: [
        { provide: ActivatedRoute, useFactory: activatedRouteStub },
        { provide: Router, useFactory: routerStub },
        { provide: CompanyDetailService, useFactory: companyDetailServiceStub }
      ]
    });
    fixture = TestBed.createComponent(CompanyDetailInfoComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it('can emit', () => {
    component.deleteCompany(1);
    component.ngOnInit()
    console.log("company detail info ",component.companyDetailList)
    expect(component.companyDetailList).toBeUndefined()
  });
});
