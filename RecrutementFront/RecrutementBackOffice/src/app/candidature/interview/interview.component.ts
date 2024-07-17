import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { CalendarView, CalendarEvent, CalendarEventTimesChangedEvent } from 'angular-calendar';
import { Interview } from 'src/app/models/interview.model';
import { InterviewService } from 'src/app/service/interview.service';
import { startOfDay, endOfDay, addHours, isSameMonth, isSameDay } from 'date-fns';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { InterviewDialogComponent } from '../interview-dialog/interview-dialog.component';
import { Subject } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/models/user.model';
import { Profile } from 'src/app/models/profile.model';
import { CandidatureService } from 'src/app/service/candidature.service';
import { ProfileService } from 'src/app/service/profile.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-interview',
  templateUrl: './interview.component.html',
  styleUrls: ['./interview.component.scss']
})
export class InterviewComponent implements OnInit{
  @ViewChild('modalContent', { static: true }) modalContent!: TemplateRef<any>;
  @ViewChild('editInterviewModal', { static: true }) editInterviewModal!: TemplateRef<any>;
  view: CalendarView=CalendarView.Month;
  viewDate: Date=new Date();
  events: CalendarEvent[]=[];
  activeDayIsOpen:boolean =true;
  CalendarView=CalendarView;
  refresh: Subject<any>=new Subject();
  selectedEvent:CalendarEvent | null=null;
  recruteurInfo:User |null=null;
  candidateProfileInfo:Profile | null=null;
  candidateUserInfo:User | null=null;
  editInterviewForm!:FormGroup;
  recruteur:User[]=[];
  jobMatchingScores=[
    {label: 'Skill',value:80},
    {label: 'Experience',value:70},
    {label: 'IQ Test',value:40},
    {label: 'Technical Test',value:40}
  ];
  totalPercentage=0;
  constructor(private interviewService: InterviewService,
    private modal: NgbModal,
    private userService:UserService,
    private candidateService:CandidatureService,
    private profileService:ProfileService,
    private modalService: NgbModal,
    private fb: FormBuilder){}
  ngOnInit():void{
    this.loadEvents();
    this.loadRecruteur();
    this.initializeForm();
    this.calculateTotalPercentage();
  }
  initializeForm():void{
    this.editInterviewForm=this.fb.group({
      dateMeet: ['',Validators.required],
      lienMeet: ['',Validators.required],
      commentaire: ['',Validators.required],
      score: ['',[Validators.required, Validators.min(0),Validators.max(100)]],
      recruteurId: [null,Validators.required],
    })
  }
  loadRecruteur():void{
    this.userService.getAllRH().subscribe((data)=>{
      
      this.recruteur=data;

    })
  }
  openEditInterviewModal():void{
    
    if(this.selectedEvent && this.selectedEvent.meta && this.selectedEvent.meta.interview){
      const interview: Interview=this.selectedEvent.meta.interview;
      this.editInterviewForm.patchValue({
        dateMeet: interview.dateMeet,
        lienMeet:interview.lienMeet,
        commentaire:interview.commentaire,
        score: interview.score,
        recruteurId:interview.recruteurId
      });
      this.modal.open(this.editInterviewModal);
    }
    
  }
    onSubmitInterview():void{
      
      if(this.editInterviewForm.valid && this.selectedEvent && this.selectedEvent.meta && this.selectedEvent.meta.interview){
        const interview: Interview=this.selectedEvent.meta.interview;
        const interviewData={
          ...interview,
          dateMeet: this.editInterviewForm.value.dateMeet,
          lienMeet:this.editInterviewForm.value.lienMeet,
          commentaire:this.editInterviewForm.value.commentaire,
          score: this.editInterviewForm.value.score,
          recruteurId:this.editInterviewForm.value.recruteurId
        };
        
        console.log(interviewData)
        this.interviewService.modifierInterview(interviewData).subscribe(()=>{
          this.modalService.dismissAll();
          this.loadEvents();
        })
      }
    }
    deleteInterview():void{
      if(this.selectedEvent && this.selectedEvent.meta && this.selectedEvent.meta.interview){
        const interview: Interview=this.selectedEvent.meta.interview;
        this.interviewService.supprimerInterview(interview.id).subscribe(()=>{
          this.modalService.dismissAll();
          this.loadEvents();
        })
      }
    }
  loadEvents():void{
    this.interviewService.afficherInterviews().subscribe((data)=>{
      this.events=data.map(interview=>({
        start: new Date(interview.dateMeet),
        title: interview.lienMeet,
        color: {primary: '#1e90ff',secondary: '#D1E8FF'},
        meta:{interview}
      }));
    })
  }
  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }
  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }
  handleEvent(action: string, event: CalendarEvent): void {

    
    this.selectedEvent=event;
    const interview:Interview=event.meta.interview;
    this.userService.getUserById(interview.recruteurId).subscribe((data)=>{
      this.recruteurInfo=data;
      this.modal.open(this.modalContent, { size: 'lg' });
    })
    this.candidateService.afficherCandidatureParId(interview.candidatureId).subscribe((data)=>{
      this.profileService.afficherProfileParId(data.profileId).subscribe((profile)=>{
        this.candidateProfileInfo=profile;
        this.userService.getUserById(profile.userId).subscribe((user)=>{
          this.candidateUserInfo=user;
        })
      })
    })
  }
  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }
  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
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
}