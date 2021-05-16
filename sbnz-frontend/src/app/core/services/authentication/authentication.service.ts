import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../model/User';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {
	private headers = new HttpHeaders({'Content-Type': 'application/json'});

	constructor(
		private http: HttpClient
	) { }

	login(auth: any): Observable<any> {
		return this.http.post(`${environment.baseUrl}/${environment.login}`, {username: auth.username, password: auth.password}, {headers: this.headers, responseType: 'json'});
	}

	isLoggedIn(): boolean {
		if (!localStorage.getItem('user')) {
				return false;
		}
		return true;
	}

	register(user: User): Observable<any> {
		return this.http.post(`${environment.baseUrl}/${environment.signUp}`, user, {headers: this.headers, responseType: 'json'});
	}

	registerAdmin(user: User): Observable<any> {
		return this.http.post(`${environment.baseUrl}/${environment.signUpAdmin}`, user, {headers: this.headers, responseType: 'json'});
	}

	signOut(): Observable<any> {
		return this.http.get(`${environment.baseUrl}/${environment.signOut}`, {headers: this.headers});
	
	}

	changePassword(passwordData: any): Observable<any> {
		return this.http.post(`${environment.baseUrl}/${environment.changePassword}`, {oldPassword: passwordData.oldPassword, newPassword: passwordData.newPassword}, {headers: this.headers, responseType: 'json'});
	}


}
