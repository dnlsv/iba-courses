import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TodosComponent } from './components/todos/todos.component';
import { TodoItemComponent } from './components/todo-item/todo-item.component';

import { DataService } from './data.service';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';

import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './components/layout/header/header.component';
import { FormsModule } from '@angular/forms';
import { AddTodoComponent } from './components/add-todo/add-todo.component';
import { AppRoutingModule } from './app-routing.module';
import { AboutComponent } from './components/about/about.component';

@NgModule({
  declarations: [
    AppComponent,
    TodosComponent,
    TodoItemComponent,
    HeaderComponent,
    AddTodoComponent,
    AboutComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientInMemoryWebApiModule.forRoot(DataService, {
      post204: false,
      put204: false,
      delay: 1000,
    }),
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
