import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Test } from '../models/test.model';
import { TestService } from '../service/test.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Question } from '../models/question.model';
import { QuestionService } from '../service/question.service';

@Component({
  selector: 'app-testoffers',
  templateUrl: './testoffers.component.html',
  styleUrls: ['./testoffers.component.scss']
})
export class TestoffersComponent implements OnInit{
  tests!: Test[];
  search: string = '';
  selectedTest!:Test | null;
  @ViewChild('questionsModal',{static:true}) questionsModal!:TemplateRef<any>;
  

  constructor(private testService: TestService,
     private router: Router,
     private modalService:NgbModal,
    private questionService:QuestionService) {}

  ngOnInit(): void {
    this.testService.afficherTests().subscribe(data => {
      this.tests = data;
    });
  }

  deleteTest(id: number | null): void {
    if (id != null) {
      this.testService.supprimerTest(id).subscribe(() => {
        this.testService.afficherTests().subscribe(data => {
          this.tests = data;
        });
      });
    }
  }

  updateTest(id: number | null): void {
    if (id != null) {
      this.router.navigate(['/testoffers/update', id]);
    }
  }

  gotoAddTest(): void {
    this.router.navigate(['/testoffers/add']);
  }

  filterTests(): void {
    // Implement your filtering logic here
  }
  openQuestionsModal(test:Test):void{
    this.selectedTest=test;
    this.modalService.open(this.questionsModal);
  }
  addQuestion(questionText:string):void{
    if (this.selectedTest){
      const newQuestion: Question={id:0,testId:this.selectedTest.id,question:questionText,options:[],reponseCorrecte:'',note:0};
      this.selectedTest.questions.push(newQuestion);
    }
  }
  deleteQuestion(i:number):void{
    if(this.selectedTest){
      const question=this.selectedTest.questions[i];
      if(question.id !==0){
        this.questionService.supprimerQuestion(question.id).subscribe(()=>{
          this.selectedTest?.questions.splice(i,1);
        });
      }
      else{
        this.selectedTest.questions.slice(i,1);
      }
    }
  }
  addOption(question:Question,option:string):void{
    question.options.push(option);
  }
  saveChanges():void{
    if(this.selectedTest){
      const index=this.tests.findIndex(test=>test.id===this.selectedTest?.id);
      if(index!=-1){
        this.tests[index].questions=this.selectedTest.questions;
        this.selectedTest.questions.forEach(question=>{
          if(question.id===0){
            this.questionService.ajouterQuestion(question).subscribe();
          }else{
            this.questionService.modifierQuestion(question).subscribe();
          }
        })
        this.testService.modifierTest(this.tests[index],this.selectedTest.id).subscribe(()=>{
          this.modalService.dismissAll();
        })
      }
    }
  }

  navigateToDetails(id: number | null): void {
    if (id != null) {
      this.router.navigate(['/testoffers/details', id]);
    }
  }
}
