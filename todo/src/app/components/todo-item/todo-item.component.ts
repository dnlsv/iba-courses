import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Todo } from 'src/app/models/Todo';

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.css']
})
export class TodoItemComponent implements OnInit {

 @Input()
  todo!: Todo;

  @Output() onCheckboxToggle: EventEmitter<Todo> = new EventEmitter();
  @Output() onDeleteTodo: EventEmitter<Todo> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  setClasses() {
    const classes = {
      todo: true,
      'is-complited': this.todo.completed
    }
    return classes;
  }

  onToggle(todo : Todo) : void {
    this.onCheckboxToggle.emit(todo);
  }

  onDelete(todo : Todo) : void {
    this.onDeleteTodo.emit(todo);
  }

}
