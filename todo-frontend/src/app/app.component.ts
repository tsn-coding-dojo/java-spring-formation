import { Component, OnDestroy, OnInit } from '@angular/core';
import { TodoService } from './services/todo.service';
import { SessionDto, TodoDto } from './models/rest-api.model';
import { GlobalErrorService } from './services/global-error.service';
import { UserService } from './services/user.service';
import cloneDeep from 'lodash.clonedeep';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  
  destroy$: Subject<boolean> = new Subject<boolean>();

  session: SessionDto | undefined;

  // Login form
  login: string;
  password: string;

  todos: TodoDto[] = [];
  todo: TodoDto = new TodoDto();

  constructor(private todoService: TodoService, public globalErrorService: GlobalErrorService, public userService: UserService ) {}

  public ngOnInit() {
    this.refresh();
  }

  public ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  // LOGIN

  submitLogin() {
    this.userService.login(this.login, this.password)
      .pipe(takeUntil(this.destroy$))
      .subscribe((session: SessionDto) => {
        this.login = '';
        this.password = '';
        this.session = session;
        sessionStorage.setItem('session', session.login);
      });
  }

  logout() {
    this.userService.logout()
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.session = undefined;
        sessionStorage.removeItem('session');
      });
  }

  // TODOS

  public refresh() {
    const existingSession = sessionStorage.getItem('session')
    this.session = existingSession 
      ? { login: existingSession } 
      : undefined;
    this.todo = new TodoDto();
    this.todoService.findAll()
      .pipe(takeUntil(this.destroy$))
      .subscribe(todos => this.todos = todos);
  }

  public cancel() {
    this.todo = new TodoDto();
  }

  public submitCreateOrUpdate() {
    if (!this.todo.id) {
      // CREATE
      this.todoService.create(this.todo)
        .pipe(takeUntil(this.destroy$))
        .subscribe(() => {
          this.todo = new TodoDto();
          this.refresh();
        });
    } else {
      // UPDATE
      this.todoService.update(this.todo)
        .pipe(takeUntil(this.destroy$))
        .subscribe(() => {
          this.todo = new TodoDto();
          this.refresh();
        });
    }
    this.globalErrorService.clear();
  }

  public complete(todo: TodoDto) {
    this.todoService.complete(todo)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.todo = new TodoDto();
        this.refresh();
      });
  }

  public update(todo: TodoDto) {
    this.todo = cloneDeep(todo);
  }

  public delete(todo: TodoDto) {
    this.todoService.delete(todo)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.todo = new TodoDto();
        this.refresh();
      });
  }

  public deleteAll() {
    this.todoService.deleteAll()
      .pipe(takeUntil(this.destroy$))
      .subscribe(todos => this.todos = todos);
  }

}
