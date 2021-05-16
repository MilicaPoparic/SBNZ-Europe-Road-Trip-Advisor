import { ComponentFixture, TestBed } from '@angular/core/testing';
import {  Router, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { fakeAsync, tick } from '@angular/core/testing';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { MatCardModule } from '@angular/material/card';
import { LoginPageComponent } from './login-page.component';
import { BrowserModule, By } from '@angular/platform-browser';
import { of } from 'rxjs';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('LoginPageComponent', () => {
  let component: LoginPageComponent;
  let fixture: ComponentFixture<LoginPageComponent>;
  let authenticationService: any;
  let router: any;
  let toastr: any;

 beforeEach(() => {

    let authenticationServiceMock ={
      login: jasmine.createSpy('login').and.returnValue(of({
        accessToken: "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiUk9MRV9SRUdJU1RFUkVEX1VTRVIiLCJpc3MiOiJzcHJpbmctc2VjdXJpdHktZXhhbXBsZSIsInN1YiI6ImF0QGdtYWlsLmNvbSIsImF1ZCI6IndlYiIsImlhdCI6MTYxMTM1Njk5NiwiZXhwIjoxNjExMzU4Nzk2fQ.zfS9kgvCNirTMIXdQRW3cKkdrDyN6sPZNGWB8kO7Z0GxFMV2BDK-uPDqyPyvTX2tskBa3ug3-nCWoHk-LHbzCg",
      
        expiresIn: 500000
      }))
    }
    let routerMock= {
        navigate: jasmine.createSpy('navigate')
    }

    const toastrMocked = {
        success: jasmine.createSpy('success'),
        error: jasmine.createSpy('error')
      };

    TestBed.configureTestingModule({
       declarations: [ LoginPageComponent ],
       imports: [ FormsModule, ReactiveFormsModule, RouterModule, ToastrModule.forRoot(), MatCardModule, BrowserModule, BrowserAnimationsModule],
       providers:    [ 
        { provide: AuthenticationService, useValue: authenticationServiceMock },
        { provide: Router, useValue: routerMock },
        { provide: ToastrService, useValue: toastrMocked },
       ]
    });

    fixture = TestBed.createComponent(LoginPageComponent);
    component = fixture.componentInstance;
    authenticationService = TestBed.inject(AuthenticationService);
    router = TestBed.inject(Router);
    toastr = TestBed.inject(ToastrService);
  }); 

  it('should create component', fakeAsync(() => {
    expect(component).toBeTruthy();
  }));

  it('should be initialized', () => {
    component.ngOnInit();
    expect(component.form).toBeDefined();
    expect(component.form.invalid).toBeTruthy();
  });

  it('should set input in reactive form', fakeAsync(() => {
    fixture.detectChanges();  // ngOnInit will be called
    fixture.whenStable().then(() => {
        expect(fixture.debugElement.query(By.css('#username')).nativeElement.value).toEqual('');
        expect(fixture.debugElement.query(By.css('#password')).nativeElement.value).toEqual('');
        
        let username = fixture.debugElement.query(By.css('#username')).nativeElement;
        username.value = 'admin@gmail.com';
        let password = fixture.debugElement.query(By.css('#password')).nativeElement;
        password.value = 'asdf';

        username.dispatchEvent(new Event('input')); 
        password.dispatchEvent(new Event('input'));

        let controlUsername = component.form.controls['username'];
        let controlPassword = component.form.controls['password'];

        expect(controlUsername.value).toEqual('admin@gmail.com');
        expect(controlPassword.value).toEqual('asdf');

      });

    
  }));

  it('should sign in the user', fakeAsync(() => {    
    expect(component.form.valid).toBeFalsy();
    component.form.controls['username'].setValue("admin@gmail.com");
    component.form.controls['password'].setValue("asdf");
   
    expect(component.form.valid).toBeTruthy();
    component.submit();

    expect(authenticationService.login).toHaveBeenCalledTimes(1);
    expect(router.navigate).toHaveBeenCalledWith(['/']);
    expect(toastr.success).toHaveBeenCalledTimes(1);
}));

it('should be invalid form when email is empty', () => {
    component.form.controls.username.setValue('');
    component.form.controls.password.setValue('sifra123');

    expect(component.form.invalid).toBeTruthy();
    fixture.detectChanges();

    const submitButton = fixture.debugElement.query(By.css('#submit')).nativeElement;
    expect(submitButton.disabled).toBeTruthy();
});

it('should be invalid form when password is empty', () => {
    component.form.controls.username.setValue('admin@gmail.com');
    component.form.controls.password.setValue('');

    expect(component.form.invalid).toBeTruthy();
    fixture.detectChanges();

    const submitButton = fixture.debugElement.query(By.css('#submit')).nativeElement;
    expect(submitButton.disabled).toBeTruthy();
  });

it('should be invalid form when password and username are empty', () => {
    component.form.controls.username.setValue('');
    component.form.controls.password.setValue('');

    expect(component.form.invalid).toBeTruthy();
    fixture.detectChanges();

    const submitButton = fixture.debugElement.query(By.css('#submit')).nativeElement;
    expect(submitButton.disabled).toBeTruthy();
});
   
});

