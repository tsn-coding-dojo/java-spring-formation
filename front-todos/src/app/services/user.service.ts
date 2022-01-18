import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { TodoDto, SessionDto } from '../models/rest-api.model';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/share';

@Injectable()
export class UserService {

    private serviceBaseURL = '/api/users';

    public session: SessionDto;

    constructor(private httpClient: HttpClient) {
    }

    public login(login: string, password: string): Observable<SessionDto> {

        const url = '/login';

        const options = {
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        };

        const body = 'username=' + login + '&password=' + password;

        const observable = this.httpClient.post<SessionDto>(url, body, options).share();
        observable.subscribe((sessionDto) => {
            this.session = sessionDto;
        });
        return observable;
    }

    public logout(): Observable<any> {
        const url = '/logout';

        const observable = this.httpClient.post(url, {}, { responseType: 'text' }).share();
        observable.subscribe(() => {
            this.session = null;
        });
        return observable;
    }

}
