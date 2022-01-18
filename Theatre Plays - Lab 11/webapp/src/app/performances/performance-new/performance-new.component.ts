import { Component, OnInit } from '@angular/core';
import {Performance} from "../../shared/performance.model";
import {Play} from "../../shared/play.model";
import {Actor} from "../../shared/actor.model";
import {FormControl, FormGroup} from "@angular/forms";
import {Director} from "../../shared/director.model";
import {Office} from "../../shared/office.model";
import {Location} from "@angular/common";
import {PlayService} from "../../plays/shared/play.service";
import {ActorService} from "../../actors/shared/actor.service";
import {PerformanceService} from "../shared/performance.service";

@Component({
  selector: 'app-performance-new',
  templateUrl: './performance-new.component.html',
  styleUrls: ['./performance-new.component.css']
})
export class PerformanceNewComponent implements OnInit {
  plays: Play[];
  actors: Actor[];

  performanceForm = new FormGroup( {
    play: new FormControl(new Play({playName: '',
      duration: 100,
      director: new Director('', 20, '', new Office('', ''))})),
    actor: new FormControl(new Actor({name: '', age: 20, gender: ''})),
    role: new FormControl('')
  })

  constructor(
    private location: Location,
    private performanceService: PerformanceService,
    private playService: PlayService,
    private actorService: ActorService
  ) { }

  ngOnInit(): void {
    this.getPlays();
    this.getActors();
  }

  getPlays(): void {
    this.playService.getPlays()
      .subscribe(plays => this.plays = plays);
  }

  getActors(): void {
    this.actorService.getActors()
      .subscribe(actors => this.actors = actors);
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit(): void {
    const performance: Performance = <Performance><unknown>{
      play: new Play(this.play.value),
      actor: new Actor(this.actor.value),
      role: this.role.value
    };

    this.performanceService.addPerformance(performance)
      .subscribe(() => this.goBack());
  }

  get play() {
    return this.performanceForm.get('play');
  }

  get actor() {
    return this.performanceForm.get('actor');
  }

  get role() {
    return this.performanceForm.get('role');
  }
}
