import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {PlaysComponent} from "./plays/plays.component";
import {PlayNewComponent} from "./plays/play-new/play-new.component";
import {PlayUpdateComponent} from "./plays/play-update/play-update.component";
import {ActorsComponent} from "./actors/actors.component";
import {ActorFilterComponent} from "./actors/actor-filter/actor-filter.component";
import {TheatreroomComponent} from "./theatreroom/theatreroom.component";
import {ActorFormComponent} from "./actors/actor-form/actor-form.component";
import {DirectorsComponent} from "./directors/directors.component";
import {DirectorFormComponent} from "./directors/director-form/director-form.component";
import {PerformancesComponent} from "./performances/performances.component";
import {PerformanceNewComponent} from "./performances/performance-new/performance-new.component";
import {PerformanceUpdateComponent} from "./performances/performance-update/performance-update.component";
import {ReportsComponent} from "./reports/reports.component";
import {OfficesComponent} from "./offices/offices.component";


const routes: Routes = [
  // { path: '', redirectTo: '/plays', pathMatch: 'full'},
  {path: 'plays', component: PlaysComponent},
  {path: 'play-new', component: PlayNewComponent},
  {path: 'play-update/:id', component: PlayUpdateComponent},

  {path: 'actors', component: ActorsComponent},
  {path: 'actor-form', component: ActorFormComponent},
  {path: 'actor-filter', component: ActorFilterComponent},

  {path: 'theatrerooms', component: TheatreroomComponent},

  {path: 'directors', component: DirectorsComponent},
  {path: 'director-form', component: DirectorFormComponent},

  {path: 'performances', component: PerformancesComponent},
  {path: 'performance-new', component: PerformanceNewComponent},
  {path: 'performance-update/:playId/:actorId', component: PerformanceUpdateComponent},

  {path: 'reports', component: ReportsComponent},

  {path: 'offices', component: OfficesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
