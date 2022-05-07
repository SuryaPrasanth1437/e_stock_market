import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyDetailInfoComponent } from './company-detail-info.component';

describe('CompanyDetailInfoComponent', () => {
  let component: CompanyDetailInfoComponent;
  let fixture: ComponentFixture<CompanyDetailInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompanyDetailInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyDetailInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
