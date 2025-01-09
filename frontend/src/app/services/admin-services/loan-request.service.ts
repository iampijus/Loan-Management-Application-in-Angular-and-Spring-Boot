import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoanRequestService {
  private baseUrl = 'http://localhost:8080/loan-mgmt-system/user-application';
  constructor(private http: HttpClient) {}

  getLoanRequests(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getLoanRequestById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateLoanRequest(id: string, loanStatus: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, loanStatus);
  }

  setScore(id: string, score: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/score/${id}`, score);
  }
  
}
