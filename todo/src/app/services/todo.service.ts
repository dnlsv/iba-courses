import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Todo } from '../models/Todo';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application-json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  todoUrl = 'api/todos';

  todoAdded: EventEmitter<Todo> = new EventEmitter<Todo>();

  constructor(private http: HttpClient) { }

  getTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(
      `${this.todoUrl}`
    );
  }

  putTodo(todo : Todo) {
    return this.http.put<Todo[]>(`${this.todoUrl}/${todo.id}`, todo, httpOptions);
  }

  deleteTodo(todo: Todo) {
    return this.http.delete<Todo[]>(`${this.todoUrl}/${todo.id}`, httpOptions);
  }

  addTodo(title: string) : Observable<Todo> {
    const newTodo: Partial<Todo> = {
      title,
      completed: false
    }
    return this.http.post<Todo>(
      this.todoUrl, newTodo, httpOptions
    )
  }

}
