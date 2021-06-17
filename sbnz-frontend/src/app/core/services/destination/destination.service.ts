import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { User } from '../../model/User';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { SearchParams } from '../../model/SearchParams';
import { Destination } from '../../model/Destination';


@Injectable({
	providedIn: 'root'
})
export class DestinationService {
    private headers = new HttpHeaders({'Content-Type': 'application/json'});
	private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }
	constructor(
		private http: HttpClient
    ) {}

    getOne(id: any):Observable<any>{
        return this.http.get(`${environment.baseUrl}/${environment.destination}/`+id, {headers: this.headers}).pipe(map(res=>res));
    }

    create(destination: Destination):Observable<any>{
        return this.http.post(`${environment.baseUrl}/${environment.destination}/`, destination, {headers: this.headers}).pipe(map(res=>res));
    }

    getDestination():Observable<any>{
        let queryParams = {};
		queryParams = {
			headers: this.headers,
			observe: 'response',
			params: new HttpParams()
		};
		return this.http.get(`${environment.baseUrl}/${environment.destination}/filterByUserProfile`, queryParams).pipe(map(res => res));
    }

	getDestinationBySearchParams(params: SearchParams):Observable<any>{
        return this.http.post(`${environment.baseUrl}/${environment.destination}/filterBySearchParams`, params, {headers: this.headers}).pipe(map(res=>res));
    }

    getAll(){
        return this.http.get(`${environment.baseUrl}/${environment.destination}`, {headers: this.headers}).pipe(map(res=>res));

    }
}