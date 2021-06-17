import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';
import { Category } from 'src/app/core/model/Category';
import { User } from 'src/app/core/model/User';
import { AuthenticationService } from 'src/app/core/services/authentication/authentication.service';
import { CategoryService } from 'src/app/core/services/category/category.service';
import { UserService } from 'src/app/core/services/user/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {

  form!: FormGroup;
  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  role!: string | undefined;
  locationLat!: number;
  locationLon!: number;
  interests!: Category[];
  todayDate!: Date;

  professionArray = ["employed", "student", "unemployed"];

  constructor(private fb: FormBuilder,
    private authenticationService: AuthenticationService,
    private router: Router,
    private toastr: ToastrService,
    private regUserService: UserService,
    private categoryService: CategoryService,
    public dialogRef: MatDialogRef<EditProfileComponent>,
    @Inject(MAT_DIALOG_DATA) public user: User) {
    this.createForm();
    this.form.patchValue(this.user);
  }

  ngOnInit(): void {
    this.getRole();
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

  getRole() {
    const item = localStorage.getItem('user');
    if (!item) {
      this.role = undefined;
      return;
    }
    const jwt: JwtHelperService = new JwtHelperService();
    this.role = jwt.decodeToken(item).role;
  }

  createForm() {
    this.form = this.fb.group({
      'firstName': ['', Validators.required],
      'lastName': ['', Validators.required],
      'email': ['', [Validators.required, Validators.pattern(this.emailRegx)]],
      'dateOfBirth': ['', Validators.required],
      'interests':[''],
      'locationLat':[''],
      'locationLon':[''],
      'profession':['']

    }
    )
  };

  saveChanges() {
    // this.user = this.form.value as User;
    this.user.firstName = this.form.value['firstName'];
    this.user.lastName = this.form.value['lastName'];
    this.user.email = this.form.value['email'];
    if (!this.locationLat || !this.locationLon) {     
      console.log('Old location will stay.')
    }else {  
      this.user.locationLon = this.locationLon;
      this.user.locationLat = this.locationLat;
    }

    if (!this.form.value['dateOfBirth']) {     
      console.log('Old birthday will stay.')
    }else {  
      this.user.dateOfBirth = this.form.value['dateOfBirth'];
    }

    if (!this.form.controls['interests']) {     
      console.log('Old interests will stay.')
    }else {  
      this.user.interests = this.form.value['interests'];
    }

    if (!this.form.controls['profession']) {     
      console.log('Old profession will stay.')
    }else {  
      this.user.profession = this.form.value['profession'];
    }
    console.log(this.user);
    this.regUserService.editUser(this.user as User).subscribe(
      result => {
        this.toastr.success('Profile information saved!');
        this.form.reset();
        this.dialogRef.close();
      },
      error => {
        this.toastr.error("Error saving data!");
      }
    );
  }


  cancel() {
    this.dialogRef.close();
  }

  changeProfession(event: any) {
    this.form.controls['profession'].setValue(event.value);
  }

  onSelectionInterests(event: any) {
    console.log(event);
    this.form.controls['interests'].setValue(event);
  }

  placeSelected(place: any) {
    this.locationLat = place.properties.lat;
    this.locationLon = place.properties.lon;
  }

}
