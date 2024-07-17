import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Candidature } from 'src/app/models/candidature.model';
import { Offer } from 'src/app/models/offer.model';
import { Profile } from 'src/app/models/profile';
import { AuthService } from 'src/app/services/auth.service';
import { CandidatureService } from 'src/app/services/candidature.service';
import { OfferService } from 'src/app/services/offer.service';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
    selector: 'app-job-details-page',
    templateUrl: './job-details-page.component.html',
    styleUrls: ['./job-details-page.component.scss']
})
export class JobDetailsPageComponent {

    title = 'Job Details - Jove';
    offer!:Offer;
    constructor(private titleService:Title,
        private offerService:OfferService,
        private route:ActivatedRoute,
        private authService:AuthService,
        private router:Router,
        private candidatureService:CandidatureService,
        private profileService:ProfileService) {}
    
    ngOnInit() {
        this.titleService.setTitle(this.title);
        this.route.paramMap.subscribe(params=>{
            
            const idParam=params.get('id');
            if(idParam){
                const id=+idParam;
                if(id){
                    this.offerService.afficherOfferParId(id).subscribe((data)=>{
                        this.offer=data;
                    })
                }
                
            }
            
        })
    }
    getImageUrl(filename:string):string{
        return `http://localhost:8082/api/files/get-image/${filename}`;
    }
    applyForJob(){
        if(!this.authService.isTokenAvailable()){
            this.router.navigate(['/']);
            return;
        }
        this.authService.getCurrentUser().subscribe((user)=>{
            this.profileService.afficherProfileParUserId(user.id).subscribe((profile)=>{
                console.log(profile);
                if (this.isProfileCompleted(profile)){
                    this.createCandidature(profile.id);
                }else{
                    this.router.navigate(['/candidates-dashboard/my-profile']);
                }
            },
            (error)=>{
                this.router.navigate(['/candidates-dashboard/my-profile']);
            })
        })
    }
    isProfileCompleted(profile:Profile): boolean{
        if (profile.numTel 
        && profile.address
        && profile.dateNaissance
        && profile.cv
        && profile.bio
        && profile.diplome
        && profile.githubProfile
        && profile.linkedinProfile){
            return true;
        }
        return false;
    }
    createCandidature(id:number){
        const candidature: Candidature={
            idCandidat:id,
            idOffer:this.offer.id,
            dateCandidature: new Date(),
            etat: 'EN_ATTENTE'
        };
        console.log(candidature)
        this.candidatureService.ajouterCandidature(candidature).subscribe((response)=>{
            console.log("go to iq test");
        })
    }


    
}