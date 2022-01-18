import { Component, OnInit } from '@angular/core';
import {Actor} from "../../shared/actor.model";
import {ActorService} from "../shared/actor.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-actor-filter',
  templateUrl: './actor-filter.component.html',
  styleUrls: ['./actor-filter.component.css']
})
export class ActorFilterComponent implements OnInit {

  actors: Actor[];

  constructor(
    private location: Location,
    private actorService: ActorService) { }

  ngOnInit(): void {
    this.getActors();
  }

  goBack(): void {
    this.location.back();
  }

  getActors(): void {
    this.actorService.getActors()
      .subscribe(actors => this.actors = actors);
  }

  filterActorsByName(name: string): void {
    this.actorService.filterActorsByName(name)
      .subscribe(filteredActors => this.actors = filteredActors);
  }
}
