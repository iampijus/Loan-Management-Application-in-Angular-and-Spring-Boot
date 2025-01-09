import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoanService {
  private baseUrl = 'http://localhost:8080/loan-mgmt-system/loans';

  constructor(private http: HttpClient) {}

  saveLoan(
    id: string,
    totalAmount: number,
    penaltyAmount: number,
    duration: number,
    loanStatus: string
  ): Observable<any> {
    const body = { id, totalAmount, penaltyAmount, duration, loanStatus };
    return this.http.post(`${this.baseUrl}`, body);
  }

  getAllLoanDetails(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  deleteLoanById(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  updateLoanStatus(id: string, status: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, status);
  }
}
