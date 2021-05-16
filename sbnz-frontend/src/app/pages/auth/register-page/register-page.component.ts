import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { User } from '../../../core/model/User';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  user!: User;
  form!: FormGroup;
  imageId!: number;
  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  hide = true;
  passwordError = false;

	constructor(
		private fb: FormBuilder,
    private authenticationService: AuthenticationService,
    private router: Router,
    private toastr: ToastrService
		
  ) {
    this.createForm();
  }
  
  ngOnInit(): void {
  }

  createForm() {
    this.form = this.fb.group({
      'firstName': ['', Validators.required],
      'lastName': ['', Validators.required],
      'email':['', [Validators.required, Validators.pattern(this.emailRegx)]],
      'password':['', Validators.required],
      'repeatPassword':['', [Validators.required]],
    }
  )};

    addUser(){
      this.passwordError = false;
      if(this.form.value['password'] != this.form.value['repeatPassword']){
        this.passwordError = true;
        return;
      }
      this.user = this.form.value as User;


      this.authenticationService.register(this.user as User).subscribe(
        result => {
          this.toastr.success('Check your email to confirm registration!');
          this.router.navigate(['/']);
          this.form.reset();
        },
        error => {
          console.log(error);
          this.toastr.error("Email already exists!");
        }
      );
      
      
    }
}
