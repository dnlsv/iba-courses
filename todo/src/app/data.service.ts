import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api'

@Injectable({
  providedIn: 'root'
})
export class DataService implements InMemoryDbService{

  constructor() { }

  createDb() {
      return {
          todos: [
            {
              id: 1,
              title: 'Todo 1',
              completed: false
            },
            {
              id: 2,
              title: 'Todo 2',
              completed: true
            },
            {
              id: 3,
              title: 'Todo 3',
              completed: false
            }     
      
          ]
      }
  }
  
}