import { TestBed } from '@angular/core/testing';

import { LoanTypeService } from './loan-type.service';

describe('LoanTypeService', () => {
  let service: LoanTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
