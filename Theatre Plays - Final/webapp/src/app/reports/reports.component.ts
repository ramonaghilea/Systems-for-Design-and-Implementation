import { Component, OnInit } from '@angular/core';
import {Play} from "../shared/play.model";
import {Actor} from "../shared/actor.model";
import {Director} from "../shared/director.model";
import {PlayService} from "../plays/shared/play.service";
import {ActorService} from "../actors/shared/actor.service";
import {DirectorService} from "../directors/shared/director.service";
import {Office} from "../shared/office.model";
import {OfficeService} from "../offices/shared/office.service";

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  plays: Play[];
  actors: Actor[];
  directors: Director[];
  offices: Office[];

  constructor(
    private playService: PlayService,
    private actorService: ActorService,
    private directorsService: DirectorService,
    private officeService: OfficeService
  ) { }

  ngOnInit(): void {
    this.getPlays();
    this.getActors();
    this.getDirectors();
    this.getOffices();
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

  getOffices(): void {
    this.officeService.getOfficesReport()
      .subscribe(res => this.offices = res);
  }
}
