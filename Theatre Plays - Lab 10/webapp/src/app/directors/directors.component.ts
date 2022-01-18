import { Component, OnInit } from '@angular/core';
import {Director} from "./shared/director.model";
import {DirectorService} from "./shared/director.service";

@Component({
  selector: 'app-directors',
  templateUrl: './directors.component.html',
  styleUrls: ['./directors.component.css']
})
export class DirectorsComponent implements OnInit {
  directors: Director[];

  constructor(private directorService: DirectorService) { }

  ngOnInit(): void {
    this.getDirectors();
  }

  getDirectors(): void {
    this.directorService.getDirectors()
      .subscribe(directors => this.directors = directors);
  }
}
