import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TheatreRoom} from "./theatreroom.model";

@Injectable()
export class TheatreroomService {

  private roomsUrl = 'http://localhost:8080/api/rooms';

  constructor(private httpClient: HttpClient) {
  }

  getRooms(): Observable<TheatreRoom[]> {
    return this.httpClient
      .get<Array<TheatreRoom>>(this.roomsUrl);
  }
  //
  // filterTheatreRoomsByCapacity(capacity: number): Observable<TheatreRoom[]> {
  // }

}
