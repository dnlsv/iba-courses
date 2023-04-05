import { Component, OnInit } from '@angular/core';
import { TodoService } from 'src/app/services/todo.service';
import { Todo } from '../../models/Todo';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css']
})
export class TodosComponent implements OnInit {

  todos: Todo[] = [];
  
  constructor(private todoService: TodoService) { }

  ngOnInit(): void {
    this.todoService.getTodos().subscribe(todos => {
      this.todos = todos;
    });

    this.todoService.todoAdded.subscribe(todo => {
      this.todos.push(todo);
    })
  }

  onCheckboxToggle(todo : Todo) : void {
    let todoToChange : Todo = this.todos.filter(element => element.id === todo.id)[0];
    todoToChange.completed = !todoToChange.completed;

    this.todoService.putTodo(todo).subscribe( (data) => console.log(data));

  }

  onDeleteTodo(todo : Todo) : void {
    this.todos = this.todos.filter(t => t.id !== todo.id);
    this.todoService.deleteTodo(todo).subscribe( (data) => console.log(data));
  }

}
