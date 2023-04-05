import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';

@Injectable({
  providedIn: 'root'
})
export class DataService implements InMemoryDbService {

  constructor() { }

  createDb() {
      return {
          list: [
            {
              id: 1,
              title: "First product",
              information: "Lorem ipsum dolor sit amet consectetur adipisicing elit."
            },
            {
              id: 2,
              title: "Second product",
              information: "Lorem ipsum dolor sit amet consectetur adipisicing elit."
            },
            {
              id: 3,
              title: "Third product",
              information: "Lorem ipsum dolor sit amet consectetur adipisicing elit."
            },
            {
              id: 4,
              title: "Fourth product",
              information: "Lorem ipsum dolor sit amet consectetur adipisicing elit."
            }
      
          ]
      }

  }
}