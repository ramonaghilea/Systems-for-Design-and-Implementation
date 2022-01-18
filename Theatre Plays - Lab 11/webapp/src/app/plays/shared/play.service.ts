import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";


import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Play} from "../../shared/play.model";


@Injectable()
export class PlayService {
  private playsUrl = 'http://localhost:8080/api/plays';

  constructor(private httpClient: HttpClient) {
  }

  getPlays(): Observable<Play[]> {
    return this.httpClient
      .get<Array<Play>>(this.playsUrl);
  }

  getPlaysDescByNumberOfPerformances(): Observable<Play[]> {
    const url = `${this.playsUrl}/report`;
    return this.httpClient
      .get<Array<Play>>(url);
  }

  getPlay(id: number): Observable<Play> {
    const url = `${this.playsUrl}/details/${id}`;
    return this.httpClient.get<Play>(url);
  }

  addPlay(play: Play): Observable<Play> {
    return this.httpClient
      .post<Play>(this.playsUrl, play);
  }

  deletePlay(id: number): Observable<Play> {
    const deleteUrl = `${this.playsUrl}/${id}`;
    return this.httpClient
      .delete<Play>(deleteUrl);
  }

  updatePlay(play: Play): Observable<any> {
    const url = `${this.playsUrl}/${play.id}`;
    return this.httpClient.put(url, play);
  }

}
