import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/core/model/User';
import { AuthenticationService } from 'src/app/core/services/authentication/authentication.service';
import { UserService } from 'src/app/core/services/user/user.service';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {
  user!: User;
  role!: string|undefined;
  result!: any;

  constructor(
    private userService: UserService,
    private router: Router,
    private toastr: ToastrService,
    private dialog: MatDialog,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    this.getRole();
    this.userService.getCurrentUser().subscribe(
      res => {
        this.user = res.body as User;
      }
    );
   
  }

  edit(){
    const dialogRef = this.dialog.open(EditProfileComponent , {
      width: '350px',
      data: this.user});
    dialogRef.afterClosed().subscribe(result => {
      this.userService.getCurrentUser().subscribe(
        res => {
          this.user = res.body as User;
        }
      );
    });
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


}
