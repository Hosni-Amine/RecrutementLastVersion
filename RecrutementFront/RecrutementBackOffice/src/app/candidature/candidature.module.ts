import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { AddCandidatureComponent } from './add-candidature/add-candidature.component';
import { UpdateCandidatureComponent } from './update-candidature/update-candidature.component';
import { CandidatureRoutes } from './candidature.routing';
import { RouterModule } from '@angular/router';
import { CandidatureComponent } from './candidature.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InterviewComponent } from './interview/interview.component';
import { InterviewDialogComponent } from './interview-dialog/interview-dialog.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import {  NgbModule } from '@ng-bootstrap/ng-bootstrap';
@NgModule({
  declarations: [
    CandidatureComponent,
    AddCandidatureComponent,
    UpdateCandidatureComponent,
    InterviewComponent,
    InterviewDialogComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(CandidatureRoutes),
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    CalendarModule.forRoot({provide:DateAdapter,useFactory:adapterFactory})

  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CandidatureModule { }
