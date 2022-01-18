import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Director} from "./director.model";

@Injectable()
export class DirectorService {

  private directorsUrl = 'http://localhost:8080/api/directors';

  constructor(private httpClient: HttpClient) {
  }

  getDirectors(): Observable<Director[]> {
    return this.httpClient
      .get<Array<Director>>(this.directorsUrl);
  }

  addDirector(director: Director): Observable<Director> {
    return this.httpClient
      .post<Director>(this.directorsUrl, director);
  }
}
