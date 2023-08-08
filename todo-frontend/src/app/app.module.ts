import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';

import { AppComponent } from './app.component';
import { TodoService } from './services/todo.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { GlobalErrorHandler } from './handlers/global-error.handler';
import { GlobalErrorService } from './services/global-error.service';
import { UserService } from './services/user.service';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    TodoService,
    GlobalErrorService,
    UserService,
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    },
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
