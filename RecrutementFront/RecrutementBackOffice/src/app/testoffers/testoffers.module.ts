import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddtestoffersComponent } from './addtestoffers/addtestoffers.component';
import { UpdatetestoffersComponent } from './updatetestoffers/updatetestoffers.component';
import { TestOffersRoutes } from './testoffers.routing';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AddtestoffersComponent,
    UpdatetestoffersComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(TestOffersRoutes),
    FormsModule,
    ReactiveFormsModule
  ]
})
export class TestoffersModule { }
