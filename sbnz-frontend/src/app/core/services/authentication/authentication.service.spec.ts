import { TestBed, getTestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { fakeAsync, tick} from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';

import { User } from "../../model/User";
import { AuthenticationService } from './authentication.service';


describe('AuthenticationService', () => {
  let injector;
  let authenticationService: AuthenticationService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;

  beforeEach(() => {

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
       providers:    [AuthenticationService]
    });

    injector = getTestBed();
    authenticationService = TestBed.inject(AuthenticationService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });
  
  afterEach(() => {
    httpMock.verify();
  });

   
  it('login() should query url and get a jwt token', fakeAsync(() => {  
    let token: string = "tokenvalue";
    const auth: any = { username: "email@gmail.com", password: "asdf"};
  
    authenticationService.login(auth).subscribe(res => token = res);
    const req = httpMock.expectOne('https://localhost:8443/auth/log-in');
    expect(req.request.method).toBe('POST');
    req.flush(token);
    tick();
    
    expect(token).toBeDefined();
    expect(token).toEqual("tokenvalue");
    
  }));

  it('register() should query url and return a registered user', fakeAsync(() => {  
    let user: User = {  
        firstName: 'Stefan',
        lastName: 'Stefic',
        email: 'stefa@gmail.com',
        password: 'asdf' 
      };
  
      const mockUser: User = 
      {
        id: 1,
        firstName: 'Stefan',
        lastName: 'Stefic',
        email: 'stefa@gmail.com',
        password: 'asdf',
        active:  true,
        verified: false     
      };
  
    authenticationService.register(user).subscribe(res => user = res);
    const req = httpMock.expectOne('https://localhost:8443/auth/sign-up');
    expect(req.request.method).toBe('POST');
    req.flush(mockUser);
    tick();
    
    expect(user).toBeDefined();
    expect(user.id).toEqual(1);
    expect(user.firstName).toEqual('Stefan');
    expect(user.lastName).toEqual('Stefic');
    expect(user.email).toEqual('stefa@gmail.com');
    expect(user.password).toEqual('asdf');
    expect(user.active).toEqual(true);
    expect(user.verified).toEqual(false);
    
  }));

  it('registerAdmin() should query url and return an admin', fakeAsync(() => {  
    let user: User = {  
        firstName: 'Stefan',
        lastName: 'Stefic',
        email: 'stefa@gmail.com',
        password: 'asdf' 
      };
  
      const mockUser: User = 
      {
        id: 1,
        firstName: 'Stefan',
        lastName: 'Stefic',
        email: 'stefa@gmail.com',
        password: 'asdf',
        active:  true,
        verified: true     
      };
  
    authenticationService.registerAdmin(user).subscribe(res => user = res);
    const req = httpMock.expectOne('https://localhost:8443/auth/sign-up-admin');
    expect(req.request.method).toBe('POST');
    req.flush(mockUser);
    tick();
    
    expect(user).toBeDefined();
    expect(user.id).toEqual(1);
    expect(user.firstName).toEqual('Stefan');
    expect(user.lastName).toEqual('Stefic');
    expect(user.email).toEqual('stefa@gmail.com');
    expect(user.password).toEqual('asdf');
    expect(user.active).toEqual(true);
    expect(user.verified).toEqual(true);
    
  }));

  it('signOut() should query url and sign current user out', fakeAsync(() => {  
    authenticationService.signOut().subscribe(res => {});
    const req = httpMock.expectOne('https://localhost:8443/auth/sign-out');
    expect(req.request.method).toBe('GET');
    req.flush({});
    
  }));

  
  it('changePassword() should query url and change password', fakeAsync(() => {  
    const passwordData: any = {  
        oldPassword: 'asdf',
        newPassword: 'asdfghjkl',
    };

    authenticationService.changePassword(passwordData).subscribe(res => {});
    const req = httpMock.expectOne('https://localhost:8443/auth/change-password');
    expect(req.request.method).toBe('POST');
    req.flush({});
    tick();
    
  }));

  it("should throw login error",()=> {
    const auth: any = { username: "email@gmail.com", password: "asdfghsdj"};
    let error:string = '';
    authenticationService.login(auth).subscribe(null,e => {
      error = e.statusText;
    });
    const req = httpMock.expectOne('https://localhost:8443/auth/log-in');
    expect(req.request.method).toBe('POST');
    req.flush("Bad request",{
      status: 400,
      statusText: 'Bad request'
    });
   
    expect(error.toString().indexOf("Bad request") >= 0).toBeTruthy();
  });

  it("should throw register error",()=> {
    let user: User = {  
      firstName: 'Stefan',
      lastName: 'Stefic',
      email: 'stefa@gmail.com',
      password: 'asdf' 
    };
    let error:string = '';
    authenticationService.register(user).subscribe(null,e => {
      error = e.statusText;
    });
    const req = httpMock.expectOne('https://localhost:8443/auth/sign-up');
    expect(req.request.method).toBe('POST');
    req.flush("Bad request",{
      status: 400,
      statusText: 'Bad request'
    });
   
    expect(error.toString().indexOf("Bad request") >= 0).toBeTruthy();
  });

  it("should throw register admin error",()=> {
    let user: User = {  
      firstName: 'Stefan',
      lastName: 'Stefic',
      email: 'stefa@gmail.com',
      password: 'asdf' 
    };
    let error:string = '';
    authenticationService.registerAdmin(user).subscribe(null,e => {
      error = e.statusText;
    });
    const req = httpMock.expectOne('https://localhost:8443/auth/sign-up-admin');
    expect(req.request.method).toBe('POST');
    req.flush("Bad request",{
      status: 400,
      statusText: 'Bad request'
    });
   
    expect(error.toString().indexOf("Bad request") >= 0).toBeTruthy();
  });

    it("should throw sign out error",()=> {
    let error:string = '';
    authenticationService.signOut().subscribe(null,e => {
      error = e.statusText;
    });
    const req = httpMock.expectOne('https://localhost:8443/auth/sign-out');
    expect(req.request.method).toBe('GET');
    req.flush("Bad request",{
      status: 400,
      statusText: 'Bad request'
    });
   
    expect(error.toString().indexOf("Bad request") >= 0).toBeTruthy();
  });

  it("should throw change password error",()=> {
    const passwordData: any = {  
      oldPassword: 'asdf',
      newPassword: 'asdfghjkl',
  };
    let error:string = '';
    authenticationService.changePassword(passwordData).subscribe(null,e => {
      error = e.statusText;
    });
    const req = httpMock.expectOne('https://localhost:8443/auth/change-password');
    expect(req.request.method).toBe('POST');
    req.flush("Bad request",{
      status: 400,
      statusText: 'Bad request'
    });
   
    expect(error.toString().indexOf("Bad request") >= 0).toBeTruthy();
  });

});