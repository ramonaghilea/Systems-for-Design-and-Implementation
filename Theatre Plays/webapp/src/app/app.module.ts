import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";

import { PlaysComponent } from './plays/plays.component';
import { PlayListComponent } from './plays/play-list/play-list.component';
import {PlayService} from "./plays/shared/play.service";
import { PlayNewComponent } from './plays/play-new/play-new.component';
import { PlayUpdateComponent } from './plays/play-update/play-update.component';
import { ActorsComponent } from './actors/actors.component';
import { ActorListComponent } from './actors/actor-list/actor-list.component';
import {ActorService} from "./actors/shared/actor.service";
import { ActorFilterComponent } from './actors/actor-filter/actor-filter.component';
import { TheatreroomComponent } from './theatreroom/theatreroom.component';
import {TheatreroomService} from "./theatreroom/shared/theatreroom.service";
import { ActorFormComponent } from './actors/actor-form/actor-form.component';
import { DirectorsComponent } from './directors/directors.component';
import {DirectorService} from "./directors/shared/director.service";
import { DirectorFormComponent } from './directors/director-form/director-form.component';
import {AgeValidatorDirective} from "./actors/shared/age-validator.directive";
import { PerformancesComponent } from './performances/performances.component';
import { PerformanceNewComponent } from './performances/performance-new/performance-new.component';
import {PerformanceService} from "./performances/shared/performance.service";
import { PerformanceUpdateComponent } from './performances/performance-update/performance-update.component';
import { ReportsComponent } from './reports/reports.component';
import { OfficesComponent } from './offices/offices.component';
import {OfficeService} from "./offices/shared/office.service";

@NgModule({
  declarations: [
    AppComponent,
    PlaysComponent,
    PlayListComponent,
    PlayNewComponent,
    PlayUpdateComponent,
    ActorsComponent,
    ActorListComponent,
    ActorFilterComponent,
    TheatreroomComponent,
    ActorFormComponent,
    DirectorsComponent,
    DirectorFormComponent,
    AgeValidatorDirective,
    PerformancesComponent,
    PerformanceNewComponent,
    PerformanceUpdateComponent,
    ReportsComponent,
    OfficesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [PlayService, ActorService, TheatreroomService, DirectorService, PerformanceService, OfficeService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
