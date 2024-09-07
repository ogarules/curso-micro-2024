import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Note } from './note';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  api = 'http://localhost:8080/notes'

  constructor(private http: HttpClient) { }

  findById(id: string) : Observable<Note>{
    const url = `${this.api}/${id}`;

    return this.http.get<Note>(url);
  }

  findAll() : Observable<Note[]>{
    const url = `${this.api}/user`;

    return this.http.get<Note[]>(url);
  }

  save(entity: Note) : Observable<Note>{
    if(entity.id){
      let urlEdit = `${this.api}/${entity.id.toString()}`;
      return this.http.put<Note>(urlEdit, entity);
    }
    else{
      return this.http.post<Note>(this.api, entity);
    }
  }

  delete(id: number){
    const url = `${this.api}/${id}`;
    
    return this.http.delete(url);
  }
}
