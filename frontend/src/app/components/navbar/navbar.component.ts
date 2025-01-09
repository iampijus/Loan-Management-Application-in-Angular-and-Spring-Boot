import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  @Input() menu: any[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {}

  handleLogout() {
    sessionStorage.clear();
    this.router.navigateByUrl('/login');
  }
}
