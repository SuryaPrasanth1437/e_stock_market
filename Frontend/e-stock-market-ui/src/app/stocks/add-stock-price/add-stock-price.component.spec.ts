import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStockPriceComponent } from './add-stock-price.component';

describe('AddStockPriceComponent', () => {
  let component: AddStockPriceComponent;
  let fixture: ComponentFixture<AddStockPriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddStockPriceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStockPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
