import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css'],
})
export class LayoutComponent implements OnInit {
  routesArray: any[] = [
    { path: 'dashboard', text: 'Dashboard', role: 'admin' },
    { path: 'borrowers', text: 'Borrowers', role: 'admin' },
    { path: 'loans', text: 'Loans', role: 'admin' },
    { path: 'loan-plans', text: 'Loan Plans', role: 'admin' },
    { path: 'loan-types', text: 'Loan Types', role: 'admin' },
    { path: 'user-home', text: 'Home', role: 'user' },
    { path: 'user-status', text: 'Status', role: 'user' },
  ];

  role: any;
  userRoutes: any[] = [];
  adminRoutes: any[] = [];
  constructor() {
    let user = sessionStorage.getItem('user');

    if (user) {
      this.role = JSON.parse(user).roles;
    }

    this.adminRoutes = this.routesArray.filter(
      (item: any) => item.role == 'admin'
    );
    this.userRoutes = this.routesArray.filter(
      (item: any) => item.role == 'user'
    );
  }

  ngOnInit(): void {}
}
