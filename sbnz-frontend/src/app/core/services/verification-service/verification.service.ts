import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class VerificationService {
	private headers = new HttpHeaders({'Content-Type': 'application/json'});

	constructor(
		private http: HttpClient
	) { }

	verify(token: string): Observable<any> {
		return this.http.get(`${environment.baseUrl}/${environment.verification}/${token}`, {headers: this.headers, responseType: 'text'});
	}



}