import { ComponentFixture, discardPeriodicTasks, flush, inject, TestBed } from '@angular/core/testing';
import {  Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { fakeAsync, tick } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { async, Observable, of } from 'rxjs';
import { RouterTestingModule} from '@angular/router/testing';
import { RouterLinkWithHref } from '@angular/router';
import { PostsPageComponent } from 'src/app/pages/posts/posts-page/posts-page.component';
import { LoginPageComponent } from 'src/app/pages/auth/login-page/login-page.component';
import { RegisterPageComponent } from 'src/app/pages/auth/register-page/register-page.component';
import { HomePageComponent } from 'src/app/pages/cultural-offer/home-page/home-page/home-page.component';
import { SpyLocation } from '@angular/common/testing';
import { Component, DebugElement, NO_ERRORS_SCHEMA } from '@angular/core';
import { NavbarNonAuthComponent } from './navbar-non-auth.component';
import { Location } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AppRoutingModule } from 'src/app/app-routing/app-routing.module';
import { MatIconModule } from '@angular/material/icon';
import { CarouselModule } from 'angular-bootstrap-md';


describe('NavbarNonAuthComponent', () => {
  let component: NavbarNonAuthComponent;
  let fixture: ComponentFixture<NavbarNonAuthComponent>;
  let location: Location;
  let router: Router;
  let debugElement: DebugElement;
  
  beforeEach(() => {
    
    TestBed.configureTestingModule({
       declarations: [HomePageComponent , NavbarNonAuthComponent],
       imports: [MatToolbarModule,MatIconModule,CarouselModule,RouterTestingModule.withRoutes([
            {path: '', component: HomePageComponent},
            {path: 'posts', component: PostsPageComponent},
            {path: 'login', component: LoginPageComponent},
            {path: 'register', component: RegisterPageComponent}
        ])],
        
       
    });
    router = TestBed.get(Router);
    location = TestBed.get(Location);
    fixture = TestBed.createComponent(NavbarNonAuthComponent);
    debugElement = fixture.debugElement;
    component = fixture.componentInstance;
    
  }); 
  it('should create commponent', fakeAsync(() => {
    expect(component).toBeTruthy();
  }));
  it('check redirection on news page', fakeAsync(() => {
    fixture.detectChanges();
    //we trigger a click on our link
    debugElement.query(By.css('#news')).nativeElement.click();
    tick();
    expect(location.path()).toBe('/posts');
  }));

  it('check redirection on home page', fakeAsync(() => {
    fixture.detectChanges();
    //we trigger a click on our link
    debugElement.query(By.css('#home')).nativeElement.click();
    tick();
    expect(location.path()).toBe('/');
  }));
  it('check redirection on login page', fakeAsync(() => {
    fixture.detectChanges();
    //we trigger a click on our link
    debugElement.query(By.css('#login')).nativeElement.click();
    tick();
    expect(location.path()).toBe('/login');
  }));
  it('check redirection on page for registration', fakeAsync(() => {
    fixture.detectChanges();
    //we trigger a click on our link
    debugElement.query(By.css('#register')).nativeElement.click();
    tick();
    expect(location.path()).toBe('/register');
  }));


});