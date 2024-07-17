import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Offer } from 'src/app/models/offer.model';
import { CategorieService } from 'src/app/services/categorie.service';
import { FilterService } from 'src/app/services/filter.service';
import { OfferService } from 'src/app/services/offer.service';

@Component({
    selector: 'app-jobs-grid-page',
    templateUrl: './jobs-grid-page.component.html',
    styleUrls: ['./jobs-grid-page.component.scss']
})
export class JobsGridPageComponent {

    title = 'Jobs Grid - Jove';
    offers:Offer[]=[];
    searchKeyword: string='';
    filteredOffers: Offer[]=[];
    
    constructor(private titleService:Title,private serviceOffer:OfferService,private categorieService:CategorieService,private filterService:FilterService) {}
    
    ngOnInit() {
        this.titleService.setTitle(this.title);
        this.loadOffers();
        this.filterService.jobType$.subscribe(jobType=>{
            this.filterOffers(jobType);
        })
    }
    getImageUrl(filename:string):string{
        return `http://localhost:8082/api/files/get-image/${filename}`;
    }
    filterOffers(jobType: string){
        this.filteredOffers=this.offers.filter(offer=>{
            return jobType==='' || offer.typeEmploi === jobType;
        })
    }
    loadOffers(){
        this.serviceOffer.afficherOffers().subscribe((data)=>{
            this.offers=data;
            this.filteredOffers=data;
            console.log(data);
        })
    }
    searchOffers(){
        if(this.searchKeyword.trim()!==''){
            this.serviceOffer.afficherOffersByKeyword(this.searchKeyword).subscribe((data)=>{
                this.filteredOffers=data;
            });
        }else{
            this.loadOffers();
        }
    }
    getCategorieName(id:number){
        this.categorieService.afficherCategorieParId(id).subscribe((data)=>{
            return data.nom;
        })
    }
}