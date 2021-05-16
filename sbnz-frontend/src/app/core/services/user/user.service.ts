import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { User } from '../../model/User';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


@Injectable({
	providedIn: 'root'
})
export class UserService {
    private headers = new HttpHeaders({'Content-Type': 'application/json'});
	private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }
	constructor(
		private http: HttpClient
    ) {}
    
    getCurrentUser():Observable<any>{
        let queryParams = {};
		queryParams = {
			headers: this.headers,
			observe: 'response',
			params: new HttpParams()
		};
		return this.http.get(`${environment.baseUrl}/${environment.user}/currentUser`, queryParams).pipe(map(res => res));
    }
}