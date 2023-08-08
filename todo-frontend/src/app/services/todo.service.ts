import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { TodoDto } from '../models/rest-api.model';
import { Observable } from 'rxjs';

@Injectable()
export class TodoService {

    private serviceBaseURL = '/api/todos';

    constructor(private httpClient: HttpClient) {
    }

    public findAll(): Observable<TodoDto[]> {
        const url = `${this.serviceBaseURL}/`;
        return this.httpClient.get<TodoDto[]>(url);
    }

    public create(todoDto: TodoDto): Observable<TodoDto> {
        const url = `${this.serviceBaseURL}/`;
        return this.httpClient.post<TodoDto>(url, todoDto)
    }

    public update(todoDto: TodoDto): Observable<any> {
        const url = `${this.serviceBaseURL}/${todoDto.id}`;
        return this.httpClient.put(url, todoDto);
    }

    public complete(todoDto: TodoDto): Observable<any> {
        const url = `${this.serviceBaseURL}/${todoDto.id}/complete`;

        let params = new HttpParams();
        params = params.append('version', '' + todoDto.version);

        return this.httpClient.post(url, null, { params: params });
    }

    public delete(todoDto: TodoDto): Observable<any> {
        const url = `${this.serviceBaseURL}/${todoDto.id}`;

        let params = new HttpParams();
        params = params.append('version', '' + todoDto.version);

        return this.httpClient.delete(url, { params: params } )
    }

    public deleteAll(): Observable<any> {
        const url = `${this.serviceBaseURL}/`;
        return this.httpClient.delete(url);
    }

}
