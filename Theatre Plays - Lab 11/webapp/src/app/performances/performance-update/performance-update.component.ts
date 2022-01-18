import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {Performance} from "../../shared/performance.model";
import {PerformanceService} from "../shared/performance.service";

@Component({
  selector: 'app-performance-update',
  templateUrl: './performance-update.component.html',
  styleUrls: ['./performance-update.component.css']
})
export class PerformanceUpdateComponent implements OnInit {
  performance: Performance;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private performanceService: PerformanceService
  ) { }

  ngOnInit(): void {
    this.getPerformance();
  }

  goBack(): void {
    this.location.back();
  }

  getPerformance(): void {
    const playId = +this.route.snapshot.paramMap.get('playId');
    const actorId = +this.route.snapshot.paramMap.get('actorId');
    this.performanceService.getPerformance(playId, actorId)
      .subscribe(perf => this.performance = perf);
  }

  updatePerformance(): void {
    this.performanceService.updatePerformance(this.performance)
      .subscribe(() => this.goBack());
  }
}
