import { NgModule } from '@angular/core';
import { GeoapifyGeocoderAutocompleteModule } from '@geoapify/angular-geocoder-autocomplete';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';
import { MaterialModule } from '../material-module';
// import { RegisteredUserService } from '../../core/services/registered-user/registered-user.service';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { VerificationPageComponent } from './verification-page/verification-page.component';

@NgModule({
  declarations: [LoginPageComponent, RegisterPageComponent, VerificationPageComponent, EditProfileComponent],
  imports: [ MaterialModule, GeoapifyGeocoderAutocompleteModule.withConfig('d7bf4986e27749f4b974b51be1ea926d'),
],
  exports: [LoginPageComponent, RegisterPageComponent, VerificationPageComponent],
  providers: []
})

export class AuthModule { }