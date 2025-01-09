import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserAuthServicesService {
  private baseUrl = 'http://localhost:8080/loan-mgmt-system/auth';
  constructor(private http: HttpClient) {}

  createNewUser(registerData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, registerData);
  }

  loginUser(loginData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, loginData);
  }
}
