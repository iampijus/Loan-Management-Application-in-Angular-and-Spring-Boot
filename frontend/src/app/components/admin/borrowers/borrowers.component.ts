import { Component, OnInit } from '@angular/core';
// import borrowersData from '../../../../assets/api/borrowers.json';
import { LoanRequestService } from 'src/app/services/admin-services/loan-request.service';

@Component({
  selector: 'app-borrowers',
  templateUrl: './borrowers.component.html',
  styleUrls: ['./borrowers.component.css'],
})
export class BorrowersComponent implements OnInit {
  // data: any[] = borrowersData;
  min: number = 650;
  max: number = 800;
  randomNumber: any;
  loanRequests: any;
  approveStatus: string = 'Approved';
  rejectStatus: string = 'Rejected';
  score: number;

  constructor(private loanRequestService: LoanRequestService) {}

  ngOnInit(): void {
    this.getLoanRequests();
  }

  getLoanRequests(): any {
    this.loanRequestService.getLoanRequests().subscribe((data) => {
      this.loanRequests = data;
      console.log(data);
    });
  }

  getRandomNumber(): number {
    return Math.floor(Math.random() * (this.max - this.min + 1) + this.min);
  }

  handleCreditScore(id: string) {
    this.loanRequestService.getLoanRequestById(id).subscribe((data) => {
      if (data.score == 0 || data.score == null) {
        this.randomNumber = this.getRandomNumber();
        this.loanRequestService
          .setScore(id, this.randomNumber)
          .subscribe((data) => {
            this.score = this.randomNumber;
            this.getLoanRequests();
          });
      } else {
        this.score = data.score;
        this.getLoanRequests();
      }
    });
  }

 

  handleApprove(id: string) {
    this.loanRequestService
      .updateLoanRequest(id, this.approveStatus)
      .subscribe(() => {
        this.getLoanRequests();
      });
  }

  handleReject(id: string) {
    this.loanRequestService
      .updateLoanRequest(id, this.rejectStatus)
      .subscribe(() => {
        this.getLoanRequests();
      });
  }
}
