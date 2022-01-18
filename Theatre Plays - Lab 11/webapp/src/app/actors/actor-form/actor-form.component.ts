import { Component, OnInit } from '@angular/core';
import {Actor} from "../../shared/actor.model";
import {ActorService} from "../shared/actor.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-actor-form',
  templateUrl: './actor-form.component.html',
  styleUrls: ['./actor-form.component.css']
})
export class ActorFormComponent implements OnInit {
  genders = ['female', 'male'];

  model = new Actor({name: '', age: 20, gender: this.genders[0]});

  constructor(
    private location: Location,
    private actorService: ActorService) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit() {
    this.actorService.addActor(this.model)
      .subscribe(() => this.goBack());
  }
}
