import { TestBed, getTestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { fakeAsync, tick} from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';

import { User } from "../../model/User";
import { UserService } from './user.service';

describe('UserService', () => {
  let injector;
  let userService: UserService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;

  beforeEach(() => {

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
       providers:    [UserService]
    });

    injector = getTestBed();
    userService = TestBed.inject(UserService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });
  
  afterEach(() => {
    httpMock.verify();
  });

   
  it('getCurrentUser() should query url and get the current user', fakeAsync(() => {
    let user: User = {};
    const mockUser: User = {  
        id: 1,
        firstName: 'Ana',
        lastName: 'Anic',
        email: 'aan@gmail.com',
        password: 'asdf',
        active:  true,
        verified: true,
        idImageDTO: 1,
        src: '' 
      };
  
    userService.getCurrentUser().subscribe(res => user = res.body);
    const req = httpMock.expectOne('https://localhost:8443/api/user/currentUser');
    expect(req.request.method).toBe('GET');
    req.flush(mockUser);
    tick();
    
    expect(user).toBeDefined();
    expect(user.id).toEqual(1);
    expect(user.firstName).toEqual('Ana');
    expect(user.lastName).toEqual('Anic');
    expect(user.email).toEqual('aan@gmail.com');
    expect(user.password).toEqual('asdf');
    expect(user.idImageDTO).toEqual(1);
    
  }));

  it('announceChange() should emit the event RegenerateData', fakeAsync(() => {
    let counter: number = 0;

    userService.RegenerateData$.subscribe(() =>  counter++);
    userService.announceChange();
    tick();

    expect(counter).toBe(1);
  }));

  it("should throw  error",()=> {
    let error:string = '';
    userService.getCurrentUser().subscribe(null,e => {
      error = e.statusText;
    });
    const req = httpMock.expectOne('https://localhost:8443/api/user/currentUser');
    expect(req.request.method).toBe('GET');
    req.flush("Error on server",{
      status: 404,
      statusText: 'Error on server'
    });
   
    expect(error.toString().indexOf("Error on server") >= 0).toBeTruthy();
  });
  
});