import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication/authentication.service';

@Component({
  selector: 'app-navbar-non-auth',
  templateUrl: './navbar-non-auth.component.html',
  styleUrls: ['./navbar-non-auth.component.scss']
})
export class NavbarNonAuthComponent implements OnInit {

  @Input() role: any;
  constructor(private router: Router,private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    console.log(this.role);
  }

  signOut(){
    this.authenticationService.signOut().subscribe(      
			result => {
				localStorage.removeItem('user');
				this.router.navigate(['/login']);
			}
		);
  }

}
