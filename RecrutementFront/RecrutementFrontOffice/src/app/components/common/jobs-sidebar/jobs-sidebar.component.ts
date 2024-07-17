import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Offer } from 'src/app/models/offer.model';
import { FilterService } from 'src/app/services/filter.service';
import { OfferService } from 'src/app/services/offer.service';

@Component({
    selector: 'app-jobs-sidebar',
    templateUrl: './jobs-sidebar.component.html',
    styleUrls: ['./jobs-sidebar.component.scss']
})
export class JobsSidebarComponent implements OnInit{
    offers: Offer[]=[];
    filtredOffers: Offer[]=[];
    jobType: string='';

    constructor(
        public router: Router,
        private filterService:FilterService
    ) { }
    ngOnInit(){
    }
    filterOffers(){
        this.filterService.setJobs(this.jobType);
    }

}