import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { User } from '../../../core/model/User';
import { Category } from 'src/app/core/model/Category';
import { CategoryService } from 'src/app/core/services/category/category.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {
  todayDate!: Date;
  user!: User;
  form!: FormGroup;
  imageId!: number;
  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  hide = true;
  passwordError = false;
  locationError = false;
  interests!: Category[];
  professionArray = ["employed", "student", "unemployed"];
  locationLat!: number;
  locationLon!: number;

  constructor(
    private fb: FormBuilder,
    private authenticationService: AuthenticationService,
    private categoryService: CategoryService,
    private router: Router,
    private toastr: ToastrService

  ) {
    this.createForm();
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      res => {
        console.log(res);
        this.interests = res as Category[];
        console.log(this.interests);
      }, error => {
        console.log(error.error);
      }
    );
  }

  createForm() {
    this.form = this.fb.group({
      'firstName': ['', Validators.required],
      'lastName': ['', Validators.required],
      'email': ['', [Validators.required, Validators.pattern(this.emailRegx)]],
      'password': ['', Validators.required],
      'repeatPassword': ['', [Validators.required]],
      'profession': ['', [Validators.required]],
      'interests': ['', Validators.required],
      'dateOfBirth': ['', Validators.required]
    }
    )
  }

  addUser() {
    this.passwordError = false;
    if (this.form.value['password'] != this.form.value['repeatPassword']) {
      this.passwordError = true;
      return;
    }

    if (!this.locationLat || !this.locationLon) {
      this.locationError = true;
      this.toastr.warning('Send me your location.mp3!');
      return;
    }

    this.user = this.form.value as User;
    this.user.locationLon = this.locationLon;
    this.user.locationLat = this.locationLat;

    this.authenticationService.register(this.user as User).subscribe(
      result => {
        this.toastr.success('Check your email to confirm registration!');
        this.router.navigate(['/login']);
        this.form.reset();
      },
      error => {
        console.log(error);
        this.toastr.error("Email already exists!");
      }
    );


  }

  changeProfession(event: any) {
    this.form.controls['profession'].setValue(event.value);
  }

  onSelectionInterests(event: any) {
    this.form.controls['interests'].setValue(event);
  }

  placeSelected(place: any) {
    this.locationLat = place.properties.lat;
    this.locationLon = place.properties.lon;
  }
}
