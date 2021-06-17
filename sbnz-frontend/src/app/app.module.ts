import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing/app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './pages/auth/auth.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Interceptor } from './interceptors/intercept.service';
import { NavbarNonAuthComponent } from './core/navbar-non-auth/navbar-non-auth.component';
import { MaterialModule } from './pages/material-module';
import { LoginPageComponent } from './pages/auth/login-page/login-page.component';
import { RegisterPageComponent } from './pages/auth/register-page/register-page.component';
import { ToastrModule } from 'ngx-toastr';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ForYouListComponent } from './pages/for-you-list/for-you-list.component';
import { PersonalizeSearchComponent } from './pages/personalize-search/personalize-search.component';
import { GeoapifyGeocoderAutocompleteModule } from '@geoapify/angular-geocoder-autocomplete';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';import { ImageSliderComponent } from './pages/destination/image-slider/image-slider.component';
import { DestitnationDetailsComponent } from './pages/destination/destination-details/destination-details.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarNonAuthComponent,
    HomePageComponent,
    ForYouListComponent,
    PersonalizeSearchComponent,
    ProfilePageComponent,
	DestitnationDetailsComponent,
    ImageSliderComponent  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    MaterialModule,
    ToastrModule.forRoot(),
    GeoapifyGeocoderAutocompleteModule.withConfig('d7bf4986e27749f4b974b51be1ea926d'),
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}],
  bootstrap: [AppComponent],
  schemas:[NO_ERRORS_SCHEMA]
})
export class AppModule { }
