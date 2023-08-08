import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { TodoService } from './services/todo.service';
import { AppModule } from './app.module';
import { GlobalErrorHandler } from './handlers/global-error.handler';
import { GlobalErrorService } from './services/global-error.service';
import { UserService } from './services/user.service';
import { of } from 'rxjs';
import { FormsModule } from '@angular/forms';
describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AppComponent],
      imports: [FormsModule],
      providers: [
        {
          provide: TodoService,
          useValue: {
            findAll: () => of(),
          },
        },
        { provide: GlobalErrorService, useValue: {} },
        { provide: UserService, useValue: {} }
      ],
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it('should render title in a h1 tag', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain(
      'Welcome to My todos !'
    );
  }));
});
