import { Component, OnInit } from '@angular/core';
import {Play} from "../../shared/play.model";
import {PlayService} from "../shared/play.service";
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';

@Component({
  selector: 'app-play-update',
  templateUrl: './play-update.component.html',
  styleUrls: ['./play-update.component.css']
})
export class PlayUpdateComponent implements OnInit {
  play: Play;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private playService: PlayService) { }

  ngOnInit(): void {
    this.getPlay();
  }

  goBack(): void {
    this.location.back();
  }

  getPlay(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.playService.getPlay(id)
      .subscribe(play => this.play = play);
  }

  updatePlay(): void {
    this.playService.updatePlay(this.play)
      .subscribe(() => this.goBack());
  }
}
