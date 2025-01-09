import { Component, OnInit } from '@angular/core';
// import loan_plans from '../../../../assets/api/loan-plans.json';
import { LoanPlanService } from 'src/app/services/admin-services/loan-plan.service';
import { LoanPlan } from 'src/app/services/admin-services/loan-plan';
declare var $: any;

@Component({
  selector: 'app-loan-plans',
  templateUrl: './loan-plans.component.html',
  styleUrls: ['./loan-plans.component.css'],
})
export class LoanPlansComponent implements OnInit {
  // data:any[]=loan_plans;

  loan_plan: LoanPlan = new LoanPlan();
  updated_loan_plan: LoanPlan = new LoanPlan();

  loanPlans: LoanPlan[];
  constructor(private loanPlanService: LoanPlanService) {}

  ngOnInit(): void {
    this.getLoanPlans();
  }

  private getLoanPlans() {
    this.loanPlanService.getLoanPlanList().subscribe((data) => {
      console.log(data);
      this.loanPlans = data;
    });
  }

  addLoanPlan() {
    this.loanPlanService.addLoanPlan(this.loan_plan).subscribe(
      (data) => {
        if(data==null){
          alert("Please fill the loan plan form");
        }
        this.getLoanPlans();
      },
      (error) => console.log(error)
    );
  }

  editLoanPlan(id: string) {
    this.loanPlanService.getLoanPlanById(id).subscribe((data) => {
      this.updated_loan_plan = data;
    });
  }

  updateLoanPlan() {
    this.loanPlanService
      .updateLoanPlan(this.updated_loan_plan.id, this.updated_loan_plan)
      .subscribe((data) => {
        console.log(data);
        this.getLoanPlans();
      });
  }

  handleSubmit() {
    this.addLoanPlan();
    this.loan_plan = new LoanPlan();
  }

  handleUpdate() {
    this.updateLoanPlan();
    $('#exampleModal').modal('hide');
  }

  handleDelete(id: string) {
    if (window.confirm('Are you sure you want to delete this item?')) {
      this.loanPlanService.deleteLoanPlan(id).subscribe((data) => {
        console.log(data);
        this.getLoanPlans();
      });
    }
  }
}
