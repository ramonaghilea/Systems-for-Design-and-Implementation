import { Component, OnInit } from '@angular/core';
import {Performance} from "../shared/performance.model";
import {PerformanceService} from "./shared/performance.service";

@Component({
  selector: 'app-performances',
  templateUrl: './performances.component.html',
  styleUrls: ['./performances.component.css']
})
export class PerformancesComponent implements OnInit {
  performances: Performance[];

  constructor(
    private performanceService: PerformanceService
  ) { }

  ngOnInit(): void {
    this.getPerformances();
  }

  getPerformances(): void {
    this.performanceService.getPerformances()
      .subscribe(performances => this.performances = performances);
  }

  deletePerformance(playId: number, actorId: number) {
    this.performanceService.deletePerformance(playId, actorId)
      .subscribe(
        response => console.log('HTTP response', response),
        error => console.log('HTTP error', error),
        ()=> this.getPerformances());
  }

}
