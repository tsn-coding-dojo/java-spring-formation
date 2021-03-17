import { Component, OnInit } from '@angular/core';
import { TodoService } from './services/todo.service';
import { TodoDto } from './models/rest-api.model';
import * as _ from 'lodash';
import { GlobalErrorService } from './services/global-error.service';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  // Login form
  login: string;
  password: string;

  todos: TodoDto[] = [];
  todo: TodoDto = new TodoDto();

  constructor(private todoService: TodoService, public globalErrorService: GlobalErrorService, public userService: UserService ) {}

  public ngOnInit() {
    this.refresh();
  }

  // LOGIN

  submitLogin() {
    this.userService.login(this.login, this.password).subscribe(() => {
      this.login = '';
      this.password = '';
    });
  }

  logout() {
    this.userService.logout();
  }

  // TODOS

  public refresh() {
    this.todoService.findAll().subscribe(todos => this.todos = todos);
  }

  public cancel() {
    this.todo = new TodoDto();
  }

  public submitCreateOrUpdate() {
    if (!this.todo.id) {
      // CREATE
      this.todoService.create(this.todo).subscribe(() => {
        this.todo = new TodoDto();
        this.refresh();
      });
    } else {
      // UPDATE
      this.todoService.update(this.todo).subscribe(() => {
        this.todo = new TodoDto();
        this.refresh();
      });
    }
  }

  public complete(todo: TodoDto) {
    this.todoService.complete(todo).subscribe(() => {
      this.todo = new TodoDto();
      this.refresh();
    });
  }

  public update(todo: TodoDto) {
    this.todo = _.cloneDeep(todo);
  }

  public delete(todo: TodoDto) {
    this.todoService.delete(todo).subscribe(() => {
      this.todo = new TodoDto();
      this.refresh();
    });
  }

  public deleteAll() {
    this.todoService.deleteAll().subscribe(todos => this.todos = todos);
  }

}
