import { Component, OnInit } from '@angular/core';
import {PlayService} from "../shared/play.service";
import {Play} from "../../shared/play.model";
import {Location} from "@angular/common";
import {DirectorService} from "../../directors/shared/director.service";
import {Director} from "../../shared/director.model";
import {Office} from "../../shared/office.model";

@Component({
  selector: 'app-play-new',
  templateUrl: './play-new.component.html',
  styleUrls: ['./play-new.component.css']
})
export class PlayNewComponent implements OnInit {
  directors : Director[];

  directorInit = new Director('', 25, 'female', new Office('', ''));
  directorName = '';
  model = new Play({playName: '', duration: 100, director: this.directorInit});

  constructor(
    private location: Location,
    private playService: PlayService,
    private directorService: DirectorService) { }

  ngOnInit(): void {
    this.directorService.getDirectors()
      .subscribe(response => this.directors = response);
  }

  goBack(): void {
    this.location.back();
  }

  // addPlay(playName: string, duration: string) {
  //   const play: Play = <Play>{playName, duration: +duration};
  //   this.playService.addPlay(play)
  //     .subscribe(() => this.goBack());
  // }

  onSubmit() {
    // this.model.director = this.directors.filter(dir => dir.name == this.directorName)[0];
    this.playService.addPlay(this.model)
      .subscribe(() => this.goBack());
  }
}
