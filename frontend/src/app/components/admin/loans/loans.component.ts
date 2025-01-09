import { Component, OnInit } from '@angular/core';
// import loanData from '../../../../assets/api/loans.json';
import { LoanRequestService } from 'src/app/services/admin-services/loan-request.service';
import { LoanPlanService } from 'src/app/services/admin-services/loan-plan.service';
import { LoanService } from 'src/app/services/admin-services/loan.service';
declare var $: any;

@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.css'],
})
export class LoansComponent implements OnInit {
  // data: any[] = loanData;
  loans: any[];
  approvedLoans: any[];
  selectedLoanId: string = '';
  selectedLoan: any;
  selectedPlan: any;
  showAmount: boolean = false;
  totalPayableAmount: number;
  monthlyOverduePenalty: number;
  selectedStatus: string = '';
  loanId_toEdit: string;
  updatedStatus: string = '';

  constructor(
    private loanRequestService: LoanRequestService,
    private loanPlanService: LoanPlanService,
    private loanService: LoanService
  ) {}

  ngOnInit(): void {
    this.getLoanRequests();
    this.getAllLoanDetails();
  }

  getLoanRequests() {
    this.loanRequestService.getLoanRequests().subscribe((data) => {
      this.approvedLoans = data.filter(
        (item: any) => item.status === 'Approved'
      );
    });
  }

  getAllLoanDetails() {
    this.loanService.getAllLoanDetails().subscribe((data) => {
      this.loans = data;
    });
  }

  onLoanChange() {
    this.selectedLoan = this.approvedLoans.find(
      (item: any) => item.id === this.selectedLoanId
    );

    this.loanPlanService
      .getLoanPlanById(this.selectedLoan.loanDetails.plan)
      .subscribe(
        (data) => {
          this.selectedPlan = data;
        },
        (error) => console.log(error)
      );
  }

  calculateAmount() {
    this.showAmount = true;

    const principal = this.selectedLoan.loanDetails.amount;
    const time = this.selectedPlan.plan;
    const interestRate = this.selectedPlan.interest / 100;
    const penaltyRate = this.selectedPlan.penalty / 100;

    // the total payable amount

    this.totalPayableAmount = Number(
      (principal * Math.pow(1 + interestRate, time)).toFixed(3)
    );

    // monthly overdue penalty
    this.monthlyOverduePenalty = Number(
      (this.totalPayableAmount * penaltyRate).toFixed(3)
    );
  }

  handleSaveLoanDetails() {
    const id = this.selectedLoan.id;
    const payableAmount = this.totalPayableAmount;
    const penaltyAmount = this.monthlyOverduePenalty;
    const duration = this.selectedPlan.plan;
    const loanStatus = this.selectedStatus;

    console.log(duration);
    this.loanService
      .saveLoan(id, payableAmount, penaltyAmount, duration, loanStatus)
      .subscribe((data) => {
        console.log(data);
        this.getAllLoanDetails();
      });

    $('#exampleModal1').modal('hide');
  }

  // changing the color of status message for approved, realeased and denied
  getStatusClass(status: string): string {
    switch (status) {
      case 'Approved':
        return 'approved-class';
      case 'Released':
        return 'released-class';
      case 'Denied':
        return 'denied-class';
      default:
        return '';
    }
  }

  handleDeleteLoan(id: string) {
    if (window.confirm('Are you sure you want to delete this loan?')) {
      this.loanService.deleteLoanById(id).subscribe((data) => {
        this.getAllLoanDetails();
      });
    }
  }

  handleEdit(id: string) {
    this.loanId_toEdit = id;
  }

  handleLoanStatus() {
    console.log(this.updatedStatus, this.loanId_toEdit);
    this.loanService
      .updateLoanStatus(this.loanId_toEdit, this.updatedStatus)
      .subscribe((data) => {
        this.getAllLoanDetails();
      });
    $('#exampleModal2').modal('hide');
  }
}
