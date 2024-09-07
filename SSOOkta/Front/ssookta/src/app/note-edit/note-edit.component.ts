import { Component, OnInit } from '@angular/core';
import { Note } from '../note';
import { NoteService } from '../note.service';
import { ActivatedRoute, Router } from '@angular/router';
import { map, of, switchMap } from 'rxjs';

@Component({
  selector: 'app-note-edit',
  templateUrl: './note-edit.component.html',
  styleUrls: ['./note-edit.component.scss']
})
export class NoteEditComponent implements OnInit {

  public note : Note;

  constructor(public noteService: NoteService, private route: ActivatedRoute, private router:Router){

  }

  ngOnInit(): void {
      this.route.params.pipe(
        map((p:any) => p.id),
        switchMap((id:string) => {
          if(id==='new'){
            return of(new Note());
          }

          return this.noteService.findById(id);
        })
      )
      .subscribe({
        next: data => {
          this.note = data;
        },
        error: error => {
          console.error(error);
        }
      });
  }

  save(){
    this.noteService.save(this.note).subscribe({
      next: data => {
        this.note = data;
        setTimeout(() => {
          this.router.navigate(['/notes']);
        }, 5000);
      }
    })
  }

  cancel(){
    this.router.navigate(['/notes']);
  }

}
