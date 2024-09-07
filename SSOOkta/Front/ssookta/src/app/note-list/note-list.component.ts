import { Component } from '@angular/core';
import { Note } from '../note';
import { NoteService } from '../note.service';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.scss']
})
export class NoteListComponent {

  noteList: Note[];

  constructor(public noteService: NoteService){
    this.search();
  }

  search(){
    this.noteService.findAll().subscribe({
      next: data => this.noteList = data,
      error: error => {
        console.log(error);
      }
    });
  }

  delete(item : Note){
    this.noteService.delete(item.id).subscribe();
    this.search();
  }
}
