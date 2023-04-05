import { Component, OnInit } from '@angular/core';
import { TodoService } from 'src/app/services/todo.service';

@Component({
  selector: 'app-add-todo',
  templateUrl: './add-todo.component.html',
  styleUrls: ['./add-todo.component.css']
})
export class AddTodoComponent implements OnInit {

  title: string = ''; 

  constructor(private todoService: TodoService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.todoService.addTodo(this.title).subscribe(todo => {
      console.log(todo);
      this.todoService.todoAdded.emit(todo);
    })
  }

}
