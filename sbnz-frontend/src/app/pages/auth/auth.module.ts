import { NgModule } from '@angular/core';
import { MaterialModule } from '../material-module';
// import { RegisteredUserService } from '../../core/services/registered-user/registered-user.service';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { VerificationPageComponent } from './verification-page/verification-page.component';

@NgModule({
  declarations: [LoginPageComponent, RegisterPageComponent, VerificationPageComponent],
  imports: [ MaterialModule],
  exports: [LoginPageComponent, RegisterPageComponent, VerificationPageComponent],
  providers: []
})

export class AuthModule { }