import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Candidature } from 'src/app/models/candidature.model';
import { Offer } from 'src/app/models/offer.model';
import { Profile } from 'src/app/models/profile.model';
import { User } from 'src/app/models/user.model';
import { CandidatureService } from 'src/app/service/candidature.service';
import { OfferService } from 'src/app/service/offer.service';
import { ProfileService } from 'src/app/service/profile.service';
import { UserService } from 'src/app/service/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InterviewService } from 'src/app/service/interview.service';

@Component({
  selector: 'app-offer-details',
  templateUrl: './offer-details.component.html',
  styleUrls: ['./offer-details.component.scss']
})
export class OfferDetailsComponent implements OnInit {
  offer!: Offer;
  candidatureList: Candidature[] = [];
  candidatList: Profile[] = [];
  userMap: Map<number, User> = new Map<number, User>();
  selectedUser?: User;
  recruteur: User[]=[];
  interviewForm!:FormGroup;
  selectedCandidatureId!:number;
  jobMatchingScores=[
    {label: 'Skill',value:80},
    {label: 'Experience',value:70},
    {label: 'IQ Test',value:40},
    {label: 'Technical Test',value:40}
  ];
  totalPercentage=0;

  @ViewChild('jobMatchingModal', { static: true }) jobMatchingModal!: TemplateRef<any>;
  @ViewChild('addInterviewModal', { static: true }) addInterviewModal!: TemplateRef<any>;
  constructor(
    private route: ActivatedRoute,
    private offerService: OfferService,
    private candidatureService: CandidatureService,
    private profileService: ProfileService,
    private userService: UserService,
    private modalService: NgbModal,
    private fb:FormBuilder,
    private interviewService:InterviewService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    const offerId = Number(this.route.snapshot.paramMap.get('id'));
    if (offerId) {
      this.offerService.afficherOfferParId(offerId).subscribe(
        (data: Offer) => {
          this.offer = data;
          this.candidatureService.afficherCandidatureParIdOffer(offerId).subscribe(
            (candidatures) => {
              
              this.candidatureList = candidatures;
              this.candidatureList.forEach(c => {
                if (c.profileId) {
                  this.profileService.afficherProfileParId(c.profileId).subscribe((profile) => {
                    this.candidatList.push(profile);
                    this.userService.getUserById(profile.userId).subscribe((user) => {
                      this.userMap.set(profile.userId, user);
                    });
                  });
                }
              });
            }
          );
        }
      );
    }
    this.loadRecruteur();
    this.calculateTotalPercentage();
  }
  initializeForm():void{
    this.interviewForm=this.fb.group({
      dateMeet: ['',Validators.required],
      lienMeet: ['',Validators.required],
      commentaire: ['',Validators.required],
      recruteurId: [null,Validators.required],
    })
  }
  loadRecruteur():void{
    this.userService.getAllRH().subscribe((data)=>{
      
      this.recruteur=data;

    })
  }
  getImageUrl(filename: string | null): string {
    return `http://localhost:8082/api/files/get-image/${filename}`;
  }

  downloadFile(filename: string | null): void {
    const url = `http://localhost:8082/api/files/get-file/${filename}`;
    window.open(url);
  }

  setDefaultImage(event: any): void {
    event.target.src = 'assets/images/bg/bg1.jpg';
  }

  getUserFullName(userId: number | undefined): string {
    const user = this.userMap.get(userId || 0);
    return user ? `${user.nom} ${user.prenom}` : '';
  }

  getUserImageProfile(userId: number | undefined): string {
    const user = this.userMap.get(userId || 0);
    return user ? user.image_profile || '' : '';
  }

  getUserEmail(userId: number | undefined): string {
    const user = this.userMap.get(userId || 0);
    return user ? user.email || '' : '';
  }

  showJobMatchingModal(userId: number | undefined,candidatureId:number| undefined): void {
    this.selectedUser = this.userMap.get(userId || 0);
    if(candidatureId !==undefined){
      this.selectedCandidatureId=candidatureId ;
    }
    this.modalService.open(this.jobMatchingModal);
  }
  openAddInterviewModal(candidature: Candidature):void{
    if(candidature.id !==undefined){
      this.selectedCandidatureId=candidature.id ;
      this.modalService.open(this.addInterviewModal);
    }
    
  }
  updateCandidatureStatus(status:string):void{
    console.log(this.selectedCandidatureId)
    if (this.selectedCandidatureId){
      
      const candidature=this.candidatureList.find(c=>c.id===this.selectedCandidatureId);
      console.log(candidature)
      if(candidature){
        candidature.etat=status;
        this.candidatureService.modifierCandidature(candidature,this.selectedCandidatureId).subscribe(()=>{
          this.modalService.dismissAll();
        })
      }
    }
  }
  accept(){
    this.updateCandidatureStatus('ACCEPTE')
  }
  refuse(){
    this.updateCandidatureStatus('REJETE')
  }
  
  getTotalPercentageClass(percentatge:number){
    if(percentatge>=70){
      return 'green-circle';
    }
    else if(percentatge>=50){
      return 'yellow-circle';
    }else{
      return 'red-circle';
    }
  }
  getCircleClass(percentatge:number):string{
    return `c100 p${percentatge} center`;
    
  }
  getFillStyle(percentage:number):any{
    if(percentage>50){
      return {
        'transform':'rotate(180deg)'
      }
    }
  }
  calculateTotalPercentage():void{
    const total=this.jobMatchingScores.reduce((sum,score)=>sum+score.value,0);
    this.totalPercentage=Math.round(total/this.jobMatchingScores.length);

  }
  onSubmitInterview():void{
    console.log(this.interviewForm.value)
    if(this.interviewForm.valid){
      const interviewData={
        ...this.interviewForm.value,
        candidatureId: this.selectedCandidatureId,
        score:0,
        id:0
      };
      console.log(interviewData)
      this.interviewService.ajouterInterview(interviewData).subscribe(()=>{
        this.modalService.dismissAll();

      })
    }
  }
}
