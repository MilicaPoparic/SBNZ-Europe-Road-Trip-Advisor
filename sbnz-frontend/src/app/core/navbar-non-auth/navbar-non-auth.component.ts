import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar-non-auth',
  templateUrl: './navbar-non-auth.component.html',
  styleUrls: ['./navbar-non-auth.component.scss']
})
export class NavbarNonAuthComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

}
