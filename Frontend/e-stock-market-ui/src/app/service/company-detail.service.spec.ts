import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { User } from '../User';
import { CompanyDetailBean } from '../company-details/companyDetailBean';
import { CompanyDetailService } from './company-detail.service';
import { of } from 'rxjs';

describe('CompanyDetailService', () => {
  let service: CompanyDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CompanyDetailService]
    });
    service = TestBed.inject(CompanyDetailService);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });

  it('get login id', () => {
   service.setLoginId("1");
   expect(service.getLoginId()).toEqual("1")
  });

  describe('login', () => {
    it('makes expected calls', () => {
      const userStub: User = <any>{};
      const httpTestingController = TestBed.inject(HttpTestingController);
      service.login(userStub).subscribe(res => {
        expect(res).toEqual(userStub);
      });
      const req = httpTestingController.expectOne('http://localhost:8083/api/v1.0/market/authentication/authenticate');
      expect(req.request.method).toEqual('POST');
      req.flush(userStub);
      httpTestingController.verify();
    });
  });

  describe('registerCompanyDetail', () => {
    it('makes expected calls', () => {
      const httpTestingController = TestBed.inject(HttpTestingController);
      const companyDetailBeanStub: CompanyDetailBean = <any>{};
      spyOn(service, 'getToken').and.stub();
              service.registerCompanyDetail(companyDetailBeanStub).subscribe(res => {
        expect(res).toEqual(companyDetailBeanStub);
      });
      expect(service.getToken).toHaveBeenCalled();
      const req = httpTestingController.expectOne('http://localhost:8083/api/v1.0/market/company/register');
      expect(req.request.method).toEqual('POST');
      req.flush(companyDetailBeanStub);
      httpTestingController.verify();
    });
  });

  describe('getAllCompanyDetails', () => {
    it('makes expected calls', () => {
      const httpTestingController = TestBed.inject(HttpTestingController);
      spyOn(service, 'getToken').and.callThrough();
      service.getAllCompanyDetails().subscribe(res => {
        // expect(res).toEqual();
      });
      expect(service.getToken).toHaveBeenCalled();
      const req = httpTestingController.expectOne('http://localhost:8083/api/v1.0/market/company/getall');
      expect(req.request.method).toEqual('GET');
      // req.flush();
      httpTestingController.verify();
    });
  });

  describe('getAllCompanyDetails', () => {
    it('makes expected calls', () => {
      const httpTestingController = TestBed.inject(HttpTestingController);
      spyOn(service, 'deleteCompanyDetail').and.callThrough();
     service.deleteCompanyDetail("1").subscribe(res=>{

     })
      expect(service.deleteCompanyDetail).toHaveBeenCalled();
      const req = httpTestingController.expectOne('http://localhost:8083/api/v1.0/market/company/delete/1');
      expect(req.request.method).toEqual('DELETE');
      // req.flush();
      httpTestingController.verify();
    });
  });
});
