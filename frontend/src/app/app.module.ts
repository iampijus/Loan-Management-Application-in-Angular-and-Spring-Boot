import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/admin/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { LoansComponent } from './components/admin/loans/loans.component';
import { LoanPlansComponent } from './components/admin/loan-plans/loan-plans.component';
import { LoanTypesComponent } from './components/admin/loan-types/loan-types.component';
import { LayoutComponent } from './components/layout/layout.component';
import { BorrowersComponent } from './components/admin/borrowers/borrowers.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { UserFormComponent } from './components/user/user-form/user-form.component';
import { UserStatusComponent } from './components/user/user-status/user-status.component';
import { AgGridModule } from 'ag-grid-angular';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JwtInterceptorService } from './services/jwt-interceptor.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    HomeComponent,
    DashboardComponent,
    LoansComponent,
    LoanPlansComponent,
    LoanTypesComponent,
    LayoutComponent,
    BorrowersComponent,
    UserHomeComponent,
    UserFormComponent,
    UserStatusComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    AgGridModule.withComponents([]),
    BrowserAnimationsModule,
    MatButtonModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
