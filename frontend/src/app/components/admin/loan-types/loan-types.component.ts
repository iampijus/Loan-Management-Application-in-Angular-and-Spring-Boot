import { Component, OnInit } from '@angular/core';
// import loan_types from '../../../../assets/api/loan-types.json';
import { LoanType } from 'src/app/services/admin-services/loan-type';
import { LoanTypeService } from 'src/app/services/admin-services/loan-type.service';
declare var $: any;

@Component({
  selector: 'app-loan-types',
  templateUrl: './loan-types.component.html',
  styleUrls: ['./loan-types.component.css'],
})
export class LoanTypesComponent implements OnInit {
  // data: any[] = loan_types;

  loan_type: LoanType = new LoanType();
  updated_loan_type: LoanType = new LoanType();

  loanTypes: LoanType[];
  constructor(private loanTypeService: LoanTypeService) {}

  ngOnInit(): void {
    this.getLoanTypes();
  }

  getLoanTypes() {
    this.loanTypeService.getLoanTypeList().subscribe(
      (data) => {
        this.loanTypes = data;
      },
      (error) => console.log(error)
    );
  }

  addLoanType() {
    this.loanTypeService.addLoanType(this.loan_type).subscribe(
      (data) => {
        if (data == null) {
          alert('Please fill the loan type form');
        }
        this.getLoanTypes();
      },
      (error) => console.log(error)
    );
  }

  editLoanType(id: string) {
    this.loanTypeService.getLoanTypeById(id).subscribe((data) => {
      this.updated_loan_type = data;
    });
  }

  updateLoanType() {
    this.loanTypeService
      .updateLoanType(this.updated_loan_type.id, this.updated_loan_type)
      .subscribe((data) => {
        console.log(data);
        this.getLoanTypes();
      });
  }

  handleSubmit() {
    this.addLoanType();
    this.loan_type = new LoanType();
  }

  handleUpdate() {
    this.updateLoanType();
    $('#exampleModal').modal('hide');
  }

  handleDelete(id: string) {
    if (window.confirm('Are you sure you want to delete this item?')) {
      this.loanTypeService.deleteLoanType(id).subscribe((data) => {
        this.getLoanTypes();
      });
    }
  }
}
