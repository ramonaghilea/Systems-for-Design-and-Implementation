import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Performance} from "../../shared/performance.model";
import {Play} from "../../shared/play.model";

@Injectable()
export class PerformanceService {

  private performancesUrl = 'http://localhost:8080/api/performances';

  constructor(private httpClient: HttpClient) {
  }

  getPerformances(): Observable<Performance[]> {
    return this.httpClient
      .get<Array<Performance>>(this.performancesUrl);
  }

  getPerformance(playId: number, actorId: number): Observable<Performance> {
    const url = `${this.performancesUrl}/details/${playId}/${actorId}`;
    return this.httpClient.get<Performance>(url);
  }

  addPerformance(performance: Performance): Observable<Performance> {
    return this.httpClient
      .post<Performance>(this.performancesUrl, performance);
  }

  deletePerformance(playId: number, actorId: number): Observable<any> {
    const deleteUrl = `${this.performancesUrl}/${playId}/${actorId}`;
    return this.httpClient
      .delete<Performance>(deleteUrl);
  }

  updatePerformance(perf: Performance): Observable<any> {
    const url = `${this.performancesUrl}/${perf.play.id}/${perf.actor.id}`;
    return this.httpClient.put(url, perf);
  }
}
