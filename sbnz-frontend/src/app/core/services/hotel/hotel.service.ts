import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { SearchParams } from '../../model/SearchParams';
import { Hotel } from '../../model/Hotel';


@Injectable({
	providedIn: 'root'
})
export class HotelService {
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
        return this.http.get(`${environment.baseUrl}/${environment.hotel}/`+id, {headers: this.headers}).pipe(map(res=>res));
    }

    getAll(){
        return this.http.get(`${environment.baseUrl}/${environment.hotel}`, {headers: this.headers}).pipe(map(res=>res));

    }

    save(hotel: Hotel, destId: any) {
        return this.http.post(`${environment.baseUrl}/${environment.hotel}/${destId}`, hotel, {headers: this.headers}).pipe(map(res=>res));

    }
}