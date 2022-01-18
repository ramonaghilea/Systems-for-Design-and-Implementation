import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Actor} from "./actor.model";

@Injectable()
export class ActorService {

  private actorsUrl = 'http://localhost:8080/api/actors';

  constructor(private httpClient: HttpClient) {
  }

  getActors(): Observable<Actor[]> {
    return this.httpClient
      .get<Array<Actor>>(this.actorsUrl);
  }

  addActor(actor: Actor): Observable<Actor> {
    return this.httpClient
      .post<Actor>(this.actorsUrl, actor);
  }

  filterActorsByName(name: string): Observable<Actor[]> {
    const url = `${this.actorsUrl}/filter/${name}`;
    return this.httpClient
      .get<Array<Actor>>(url);
  }

  sortActorsByAge(): Observable<Actor[]> {
    const url = `${this.actorsUrl}/sort`;
    var result =  this.httpClient
      .get<Array<Actor>>(url);
    return result;
  }
}
