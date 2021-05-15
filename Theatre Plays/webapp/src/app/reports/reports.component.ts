import { Component, OnInit } from '@angular/core';
import {Play} from "../shared/play.model";
import {Actor} from "../shared/actor.model";
import {Director} from "../shared/director.model";
import {PlayService} from "../plays/shared/play.service";
import {ActorService} from "../actors/shared/actor.service";
import {DirectorService} from "../directors/shared/director.service";

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  plays: Play[];
  actors: Actor[];
  directors: Director[];

  constructor(
    private playService: PlayService,
    private actorService: ActorService,
    private directorsService: DirectorService
  ) { }

  ngOnInit(): void {
    this.getPlays();
    this.getActors();
    this.getDirectors();
  }

  getPlays(): void {
    this.playService.getPlaysDescByNumberOfPerformances()
      .subscribe(plays => this.plays = plays);
  }

  getActors(): void {
    this.actorService.getActorsDescByNumberOfPerformances()
      .subscribe(res => this.actors = res);
  }

  getDirectors(): void {
    this.directorsService.getDirectorsDescByNumberOfPlays()
      .subscribe(res => this.directors = res);
  }

}
