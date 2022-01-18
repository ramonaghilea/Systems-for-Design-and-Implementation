import { Component, OnInit } from '@angular/core';
import {Office} from "../shared/office.model";
import {OfficeService} from "./shared/office.service";

@Component({
  selector: 'app-offices',
  templateUrl: './offices.component.html',
  styleUrls: ['./offices.component.css']
})
export class OfficesComponent implements OnInit {
  offices: Office[];

  constructor(private officeService: OfficeService) { }

  ngOnInit(): void {
    this.getOffices();
  }

  getOffices(): void {
    this.officeService.getOffices()
      .subscribe(res => this.offices = res);
  }
}
