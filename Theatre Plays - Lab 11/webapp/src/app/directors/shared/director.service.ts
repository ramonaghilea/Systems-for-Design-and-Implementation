import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Director} from "../../shared/director.model";

@Injectable()
export class DirectorService {

  private directorsUrl = 'http://localhost:8080/api/directors';

  constructor(private httpClient: HttpClient) {
  }

  getDirectors(): Observable<Director[]> {
    return this.httpClient
      .get<Array<Director>>(this.directorsUrl);
  }

  getDirectorsDescByNumberOfPlays(): Observable<Director[]> {
    const url = `${this.directorsUrl}/report`;
    return this.httpClient
      .get<Array<Director>>(url);
  }

  getDirector(id: number): Observable<Director> {
    const url = `${this.directorsUrl}/details/${id}`;
    return this.httpClient.get<Director>(url);
  }

  addDirector(director: Director): Observable<Director> {
    return this.httpClient
      .post<Director>(this.directorsUrl, director);
  }

  deleteDirector(id: number): Observable<Director> {
    const deleteUrl = `${this.directorsUrl}/${id}`;
    return this.httpClient
      .delete<Director>(deleteUrl);
  }

  updateDirector(director: Director): Observable<any> {
    const url = `${this.directorsUrl}/${director.id}`;
    return this.httpClient.put(url, director);
  }
}
