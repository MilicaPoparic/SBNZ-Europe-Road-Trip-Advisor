import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { User } from '../../model/User';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { SearchParams } from '../../model/SearchParams';


@Injectable({
	providedIn: 'root'
})
export class CategoryService {
    private headers = new HttpHeaders({'Content-Type': 'application/json'});
	private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }
	constructor(
		private http: HttpClient
    ) {}

	getCategories():Observable<any>{
        return this.http.get(`${environment.baseUrl}/${environment.category}`, {headers: this.headers}).pipe(map(res=>res));
    }
}