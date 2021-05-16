import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'sbnz-frontend';
  public role!: string| undefined;

  constructor(private router: Router) {}

  checkRole() {
	const item = localStorage.getItem('user');

	if (!item) {
		this.role = undefined;
		return;
	}

	const jwt: JwtHelperService = new JwtHelperService();
	this.role = jwt.decodeToken(item).role;
  }
}
