import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoanType } from './loan-type';

@Injectable({
  providedIn: 'root',
})
export class LoanTypeService {
  private baseUrl = 'http://localhost:8080/loan-mgmt-system/loan-types';

  constructor(private http: HttpClient) {}

  getLoanTypeList(): Observable<LoanType[]> {
    return this.http.get<LoanType[]>(`${this.baseUrl}`);
  }

  addLoanType(loanType: LoanType): Observable<any> {
    return this.http.post(`${this.baseUrl}`, loanType);
  }

  getLoanTypeById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateLoanType(id: string, loanType: LoanType): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, loanType);
  }

  deleteLoanType(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
