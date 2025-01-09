import { Component, OnInit } from '@angular/core';
// import loan_cards from '../../../../assets/api/user-cards.json';
import { Router } from '@angular/router';
import { UserServicesService } from 'src/app/services/user-services/user-services.service';
import { Loan } from 'src/app/services/user-services/loan';
@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css'],
})
export class UserHomeComponent implements OnInit {
  // data: any[] = loan_cards;

  loans: Loan[];

  constructor(
    private router: Router,
    private userServicesService: UserServicesService
  ) {}

  ngOnInit(): void {
    this.getLoans();
  }

  getLoans() {
    this.userServicesService.getAllLoans().subscribe((data) => {
      this.loans = data;
    });
  }

  navigateToForm(loanType: string) {
    this.router.navigate(['/user-application-form', { loanType: loanType }]);
  }
}
