import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  form: FormGroup;

	constructor(
		private fb: FormBuilder,
		private authenticationService: AuthenticationService,
		private router: Router,
		private toastr: ToastrService
	) {
		this.form = this.fb.group({
			username : [null, Validators.required],
			password: [null, Validators.required]
    });
    
	}

	ngOnInit() {
	}

	submit() {
		const auth: any = {};
		auth.username = this.form.value['username'];
    	auth.password = this.form.value['password'];
		this.authenticationService.login(auth).subscribe(      
			result => {
				this.toastr.success('Successful login!');
				localStorage.setItem('user', JSON.stringify(result));
				if(this.checkRole()){
					this.router.navigate(['/destinations']);

				}else {
					this.router.navigate(['/']);

				}
			},
			error => {
				console.log(error);
				this.toastr.error('Wrong password or username');
			}
		);
	}

	checkRole() {
		const item = localStorage.getItem('user');
	
		if (!item) {
			return;
		}
	
		const jwt: JwtHelperService = new JwtHelperService();
		let role = jwt.decodeToken(item).role;
		if(role == 'ROLE_ADMIN') {
			return true;
		}
		return false;
	  }

}
