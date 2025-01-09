import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthServicesService } from 'src/app/services/user-services/user-auth-services.service';
declare var $: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  registerData: any;
  loginData: any;
  user: any;

  constructor(
    private router: Router,
    private userAuthServicesService: UserAuthServicesService
  ) {}

  ngOnInit(): void {}

  // Login

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  handleLogin() {
    this.loginData = this.loginForm.value;
    this.userAuthServicesService.loginUser(this.loginData).subscribe(
      (data) => {
        sessionStorage.setItem('user', JSON.stringify(data));
        let user = sessionStorage.getItem('user');
        if (user) {
          this.user = JSON.parse(user);
        }
        if (this.user.roles.includes('ROLE_ADMIN')) {
          this.router.navigate(['/dashboard']);
        } else if (this.user.roles.includes('ROLE_USER')) {
          this.router.navigate(['/user-home']);
        }
      },
      (error) => {
        alert('Invalid Credentials');
        this.loginForm.reset();
      }
    );
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

  // Register
  registerForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    contact: new FormControl('', [
      Validators.required,
      Validators.pattern('[0-9]{10}'),
    ]),
    password: new FormControl('', [Validators.required]),
  });

  handleRegister() {
    this.registerData = this.registerForm.value;
    this.userAuthServicesService.createNewUser(this.registerData).subscribe(
      (data) => {
        console.log(data);
      },
      (error) => alert('User Already Exists')
    );

    //close the bootstrap modal
    $('#exampleModal').modal('hide');
  }

  get r_name() {
    return this.registerForm.get('name');
  }

  get r_email() {
    return this.registerForm.get('email');
  }
  get r_contact() {
    return this.registerForm.get('contact');
  }
  get r_password() {
    return this.registerForm.get('password');
  }
}
