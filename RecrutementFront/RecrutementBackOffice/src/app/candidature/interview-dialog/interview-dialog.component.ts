import { Component,Inject, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Interview } from 'src/app/models/interview.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { InterviewService } from 'src/app/service/interview.service';
@Component({
  selector: 'app-interview-dialog',
  templateUrl: './interview-dialog.component.html',
  styleUrls: ['./interview-dialog.component.scss']
})
export class InterviewDialogComponent {
  
}
