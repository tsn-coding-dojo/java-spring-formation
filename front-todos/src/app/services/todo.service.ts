import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { TodoDto } from '../models/rest-api.model';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/share';

@Injectable()
export class TodoService {

    private serviceBaseURL = '/api/todos';

    constructor(private httpClient: HttpClient) {
    }

    public findAll(): Observable<TodoDto[]> {
        const url = `${this.serviceBaseURL}/`;
        const observable = this.httpClient.get<TodoDto[]>(url).share();
        observable.subscribe();
        return observable;
    }

    public create(todoDto: TodoDto): Observable<TodoDto> {
        const url = `${this.serviceBaseURL}/`;
        const observable = this.httpClient.post<TodoDto>(url, todoDto).share();
        observable.subscribe();
        return observable;
    }

    public update(todoDto: TodoDto): Observable<any> {
        const url = `${this.serviceBaseURL}/${todoDto.id}`;
        const observable = this.httpClient.put(url, todoDto).share();
        observable.subscribe();
        return observable;
    }

    public complete(todoDto: TodoDto): Observable<any> {

        console.error('complete2');

        const url = `${this.serviceBaseURL}/${todoDto.id}/complete`;

        let params = new HttpParams();
        params = params.append('version', '' + todoDto.version);

        const observable = this.httpClient.post(url, null, { params: params }).share();
        observable.subscribe();
        return observable;
    }

    public delete(todoDto: TodoDto): Observable<any> {
        const url = `${this.serviceBaseURL}/${todoDto.id}`;

        let params = new HttpParams();
        params = params.append('version', '' + todoDto.version);

        const observable = this.httpClient.delete(url, { params: params } ).share();
        observable.subscribe();
        return observable;
    }

    public deleteAll(): Observable<any> {
        const url = `${this.serviceBaseURL}/`;
        const observable = this.httpClient.delete(url ).share();
        observable.subscribe();
        return observable;
    }

}
