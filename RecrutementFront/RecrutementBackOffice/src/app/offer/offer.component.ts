import { Component, OnInit } from '@angular/core';
import { Offer } from '../models/offer.model';
import { OfferService } from '../service/offer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.scss']
})
export class OfferComponent implements OnInit{
  offers!:Offer[];
  search:string='';
  constructor(private serviceOffer:OfferService,private router:Router){}
  ngOnInit(): void {
    this.serviceOffer.afficherOffers().subscribe(data=>{
      this.offers=data;
    })
  }
  deleteOffer(id:number | null){
    if(id!=null){
      this.serviceOffer.supprimerOffer(id).subscribe(()=>{
        this.serviceOffer.afficherOffers().subscribe(data=>{
          this.offers=data;
        })
      })
    }
   

  }
  updateOffer(id:number | null){
    if(id!=null){
      // Navigate to the update offer page with the offer ID
      this.router.navigate(['/offer/update', id]);
    }
  }
  
  gotoAddOffer(){
    this.router.navigate(['/offer/add']);
  }
  filterOffers(){

  }
  getImageUrl(filename:string):string{
      return `http://localhost:8082/api/files/get-image/${filename}`;
  }
  setDefaultImage(event:any){
    event.target.src='assets/images/bg/bg3.jpg'

  }
  navigateToDetails(id:number | null):void{
    this.router.navigate(['/offer/details',id]);
  }

}
