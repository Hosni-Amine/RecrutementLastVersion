import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { AddOfferComponent } from './add-offer/add-offer.component';
import { UpdateOfferComponent } from './update-offer/update-offer.component';
import { OfferRoutes } from './offer.routing';
import { RouterModule } from '@angular/router';
import { OfferComponent } from './offer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OfferDetailsComponent } from './offer-details/offer-details.component';
import {  NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    OfferComponent,
    AddOfferComponent,
    UpdateOfferComponent,
    OfferDetailsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(OfferRoutes),
    FormsModule,
    ReactiveFormsModule,
    NgbModule,

  ]
})
export class OfferModule { }
