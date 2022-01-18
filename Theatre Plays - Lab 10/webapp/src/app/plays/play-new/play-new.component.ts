import { Component, OnInit } from '@angular/core';
import {PlayService} from "../shared/play.service";
import {Play} from "../shared/play.model";
import {Location} from "@angular/common";

@Component({
  selector: 'app-play-new',
  templateUrl: './play-new.component.html',
  styleUrls: ['./play-new.component.css']
})
export class PlayNewComponent implements OnInit {

  constructor(
    private location: Location,
    private playService: PlayService) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

  addPlay(playName: string, duration: string) {
    const play: Play = <Play>{playName, duration: +duration};
    this.playService.addPlay(play)
      .subscribe(() => this.goBack());
  }
}
