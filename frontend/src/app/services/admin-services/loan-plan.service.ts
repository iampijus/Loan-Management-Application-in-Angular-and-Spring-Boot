import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoanPlan } from './loan-plan';

@Injectable({
  providedIn: 'root',
})
export class LoanPlanService {
  private baseUrl = 'http://localhost:8080/loan-mgmt-system/loan-plans';

  constructor(private http: HttpClient) {}

  getLoanPlanList(): Observable<LoanPlan[]> {
    return this.http.get<LoanPlan[]>(`${this.baseUrl}`);
  }

  addLoanPlan(loanPlan: LoanPlan): Observable<any> {
    return this.http.post(`${this.baseUrl}`, loanPlan);
  }

  getLoanPlanById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateLoanPlan(id: string, loanPlan: LoanPlan): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, loanPlan);
  }

  deleteLoanPlan(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
