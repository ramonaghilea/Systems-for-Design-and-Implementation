import {Play} from "./play.model";
import {Actor} from "./actor.model";

export class Performance {
  play: Play;
  actor: Actor;
  role: string;

  constructor(play: Play, actor: Actor, role: string) {
    this.play = play;
    this.actor = actor;
    this.role = role;
  }
}
