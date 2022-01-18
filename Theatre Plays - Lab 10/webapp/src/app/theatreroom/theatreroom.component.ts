import { Component, OnInit } from '@angular/core';
import {Actor} from "../actors/shared/actor.model";
import {ActorService} from "../actors/shared/actor.service";
import {TheatreroomService} from "./shared/theatreroom.service";
import {TheatreRoom} from "./shared/theatreroom.model";

@Component({
  selector: 'app-theatreroom',
  templateUrl: './theatreroom.component.html',
  styleUrls: ['./theatreroom.component.css']
})
export class TheatreroomComponent implements OnInit {

  rooms: TheatreRoom[];
  allRooms: TheatreRoom[];
  filteredRooms: TheatreRoom[];

  constructor(
    private theatreRoomService: TheatreroomService) { }

  ngOnInit(): void {
    this.getRooms();
  }

  getRooms(): void {
    this.theatreRoomService.getRooms()
      .subscribe(rooms => this.rooms = rooms);
  }

  filterRoomsByCapacity(capacity: string): void {
    let capacityInt = +capacity;
    this.filteredRooms = this.rooms.filter(
      room => room.capacity >= capacityInt);

    this.allRooms = this.rooms;
    this.rooms = this.filteredRooms;
  }

  sortRoomsByCapacity(): void {
    this.rooms = this.rooms.sort(
      (room1, room2) =>
      { return room2.capacity - room1.capacity });
  }

  getAllRooms(): void {
    if(this.allRooms != null)
      this.rooms = this.allRooms;
  }
}
