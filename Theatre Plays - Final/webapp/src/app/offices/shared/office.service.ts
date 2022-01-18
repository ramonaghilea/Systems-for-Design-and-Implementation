import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Office} from "../../shared/office.model";

@Injectable()
export class OfficeService {

  private officesUrl = 'http://localhost:8080/api/offices';

  constructor(private httpClient: HttpClient) {
  }

  getOffices(): Observable<Office[]> {
    return this.httpClient
      .get<Array<Office>>(this.officesUrl);
  }

  getOfficesReport(): Observable<Office[]> {
    const url = `${this.officesUrl}/report`;
    return this.httpClient
      .get<Array<Office>>(url);
  }
}
