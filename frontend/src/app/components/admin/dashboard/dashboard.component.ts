import { Component, OnInit } from '@angular/core';
import { LoanService } from 'src/app/services/admin-services/loan.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  totalReceivable: number;
  constructor(private loanService: LoanService) {}

  ngOnInit(): void {
    this.calculateTotalReceivable();
  }

  calculateTotalReceivable() {
    this.loanService.getAllLoanDetails().subscribe((data) => {
      const releasedLoans = data.filter(
        (item: any) => item.status === 'Released'
      );
      this.totalReceivable=0;
      releasedLoans.forEach((item: any) => {
        this.totalReceivable += item.payableDetails.payable;
      });
      console.log(this.totalReceivable);
    });
  }
}
