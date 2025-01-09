import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserServicesService } from 'src/app/services/user-services/user-services.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css'],
})
export class UserFormComponent implements OnInit {
  loanType: string;
  formTitle: string;
  applicationForm: FormGroup;
  loanPlans: any;
  selected_loanplan: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private userServicesService: UserServicesService
  ) {
    this.route.params.subscribe((params) => {
      this.loanType = params.loanType;
      this.formTitle = `Application Form for ${this.loanType}`;
      this.getAllLoanPlans();
    });
  }

  ngOnInit(): void {
    this.applicationForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required]],
      contact: ['', [Validators.required]],
      address: this.formBuilder.group({
        city: ['', [Validators.required]],
        state: ['', [Validators.required]],
        country: ['', [Validators.required]],
        pin: ['', [Validators.required]],
      }),
      loanDetails: this.formBuilder.group({
        amount: ['', [Validators.required]],
        plan: ['', [Validators.required]],
      }),
      document: [''],
    });
  }

  get name() {
    return this.applicationForm.get('name');
  }

  get email() {
    return this.applicationForm.get('email');
  }

  get contact() {
    return this.applicationForm.get('contact');
  }

  get city() {
    return this.applicationForm.get('address.city');
  }

  get state() {
    return this.applicationForm.get('address.state');
  }

  get country() {
    return this.applicationForm.get('address.country');
  }

  get pin() {
    return this.applicationForm.get('address.pin');
  }

  get amount() {
    return this.applicationForm.get('loanDetails.amount');
  }

  get plan() {
    return this.applicationForm.get('loanDetails.plan');
  }

  // handle the file input change

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      const documentControl = this.applicationForm.get('document');
      if (documentControl) {
        documentControl.setValue(file);
      }
    }
  }

  getAllLoanPlans() {
    this.userServicesService.getLoanPlans().subscribe((data) => {
      this.loanPlans = data;
    });
  }

  getLoanPlanById(id: string) {
    this.userServicesService.getLoanPlanById(id).subscribe((data) => {
      this.selected_loanplan = data;
    });
  }

  handleApplicationSubmit() {
    if (this.applicationForm.valid) {
      const formData = new FormData();

      // append the file
      const documentControl = this.applicationForm.get('document');
      if (documentControl) {
        formData.append('document', documentControl.value);
      }

      // append other form data as a JSON string
      const otherData = { ...this.applicationForm.value };
      delete otherData.document;
      formData.append('otherData', JSON.stringify(otherData));

      this.userServicesService
        .submitLoanApplication(formData, this.loanType)
        .subscribe(
          (data) => {
            console.log(data);
            alert('Application submitted successfully.');
            this.applicationForm.reset();
          },
          (error) => {
            console.log(error);
            alert('Failed to submit the application.');
          }
        );
    } else {
      alert('Please fill the application form.');
    }
  }
}
