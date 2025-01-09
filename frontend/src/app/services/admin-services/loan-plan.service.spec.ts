import { TestBed } from '@angular/core/testing';

import { LoanPlanService } from './loan-plan.service';

describe('LoanPlanService', () => {
  let service: LoanPlanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanPlanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
