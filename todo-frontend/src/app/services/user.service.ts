import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SessionDto } from '../models/rest-api.model';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

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
        return this.httpClient.post<SessionDto>(url, body, options);
    }

    public logout(): Observable<any> {
        const url = '/logout';
        return this.httpClient.post(url, {}, { responseType: 'text' });
    }

}
