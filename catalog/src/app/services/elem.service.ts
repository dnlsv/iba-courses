import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Elem } from '../models/Elem';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'aplication-json',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class ElemService {
  elemUrl = 'api/list';

  flag!: boolean;
  elem!: Elem;

  constructor(private http: HttpClient) {}

  getList(): Observable<Elem[]> {
    return this.http.get<Elem[]>(`${this.elemUrl}`);
  }

  updateElem(elem: Elem) {
    return this.http.put<Elem[]>(
      `${this.elemUrl}/${elem.id}`,
      elem,
      httpOptions
    );
  }

  deleteElem(elem: Elem) {
    return this.http.delete<Elem[]>(`${this.elemUrl}/${elem.id}`, httpOptions);
  }
}
