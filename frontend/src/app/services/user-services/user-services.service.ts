import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Loan } from './loan';

@Injectable({
  providedIn: 'root',
})
export class UserServicesService {
  private baseUrl = 'http://localhost:8080/loan-mgmt-system';

  constructor(private http: HttpClient) {}

  getAllLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(`${this.baseUrl}/loan-types`);
  }

  getLoanPlans(): Observable<any> {
    return this.http.get(`${this.baseUrl}/loan-plans`);
  }

  getLoanPlanById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/loan-plans/${id}`);
  }

  submitLoanApplication(formData: any, type: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/user-application/${type}`, formData);
  }

  getLoanStatusByEmail(email: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/user-application/status`,email);
  }


}
