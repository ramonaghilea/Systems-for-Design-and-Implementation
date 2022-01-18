import { Component, OnInit } from '@angular/core';
import {Play} from "../shared/play.model";
import {PlayService} from "../shared/play.service";
import {Location} from "@angular/common";

@Component({
  moduleId: module.id,
  selector: 'app-play-list',
  templateUrl: './play-list.component.html',
  styleUrls: ['./play-list.component.css']
})
export class PlayListComponent implements OnInit {
  plays: Play[];

  constructor(
    private playService: PlayService) { }

  ngOnInit(): void {
    this.getPlays();
  }

  getPlays(): void {
    this.playService.getPlays()
      .subscribe(plays => this.plays = plays);
  }

  deletePlay(id: number): void {
    this.playService.deletePlay(id)
      .subscribe(
        response => console.log('HTTP response', response),
        error => console.log('HTTP error', error),
        ()=> this.updatePlayList(id));
  }

  updatePlayList(id: number): void {
    this.plays = this.plays.filter(
      play => play.id !== id
    );
  }
}
