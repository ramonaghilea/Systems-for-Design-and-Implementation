import { Component, OnInit } from '@angular/core';
import {Play} from "../../shared/play.model";
import {PlayService} from "../../plays/shared/play.service";
import {Actor} from "../../shared/actor.model";
import {ActorService} from "../shared/actor.service";

@Component({
  selector: 'app-actor-list',
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.css']
})
export class ActorListComponent implements OnInit {

  actors: Actor[];

  constructor(
    private actorService: ActorService) { }

  ngOnInit(): void {
    this.getActors();
  }

  getActors(): void {
    this.actorService.getActors()
      .subscribe(actors => this.actors = actors);
  }

  sortActorsByAge(): void {
    this.actorService.sortActorsByAge()
      .subscribe(sortedActors => this.actors = sortedActors);
  }
}
