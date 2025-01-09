import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrowersComponent } from './borrowers.component';

describe('BorrowersComponent', () => {
  let component: BorrowersComponent;
  let fixture: ComponentFixture<BorrowersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BorrowersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrowersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
