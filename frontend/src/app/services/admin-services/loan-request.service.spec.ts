import { TestBed } from '@angular/core/testing';

import { LoanRequestService } from './loan-request.service';

describe('LoanRequestService', () => {
  let service: LoanRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
