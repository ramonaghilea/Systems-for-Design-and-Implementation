import {Director} from "./director.model";

export class Play {
  id: number;
  playName: string;
  duration: number;
  director: Director;

  // constructor(playName: string, duration: number, director: Director) {
  //   this.playName = playName;
  //   this.duration = duration;
  //   this.director = director;
  // }

  public constructor(init?: Partial<Play>) {
    Object.assign(this, init);
  }

}
