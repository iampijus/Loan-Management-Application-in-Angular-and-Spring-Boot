import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { LoansComponent } from './components/admin/loans/loans.component';
import { LoanPlansComponent } from './components/admin/loan-plans/loan-plans.component';
import { LoanTypesComponent } from './components/admin/loan-types/loan-types.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/admin/home/home.component';
import { LayoutComponent } from './components/layout/layout.component';
import { BorrowersComponent } from './components/admin/borrowers/borrowers.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { UserFormComponent } from './components/user/user-form/user-form.component';
import { UserStatusComponent } from './components/user/user-status/user-status.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },

  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'home', component: HomeComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'borrowers', component: BorrowersComponent },
      { path: 'loans', component: LoansComponent },
      { path: 'loan-plans', component: LoanPlansComponent },
      { path: 'loan-types', component: LoanTypesComponent },
      { path: 'user-home', component: UserHomeComponent },
      { path: 'user-application-form', component: UserFormComponent },
      { path: 'user-status', component: UserStatusComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
