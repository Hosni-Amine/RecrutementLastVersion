import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Test, TestType } from 'src/app/models/test.model';
import { QuestionService } from 'src/app/service/question.service';
import { TestService } from 'src/app/service/test.service';

@Component({
  selector: 'app-updatetestoffers',
  templateUrl: './updatetestoffers.component.html',
  styleUrls: ['./updatetestoffers.component.scss']
})
export class UpdatetestoffersComponent implements OnInit{
  testForm: FormGroup;
  testId: number | null = null;
  testTypes = Object.values(TestType);

  constructor(
    private fb: FormBuilder,
    private testService: TestService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.testForm = this.fb.group({
      type: ['', Validators.required],
      technologie: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      this.testId = Number(id);
      if (this.testId) {
        this.testService.afficherTestParId(this.testId).subscribe(test => {
          this.testForm.patchValue(test);
        });
      }
    });
  }

  onSubmit() {
    if (this.testForm.valid) {
      const updatedTest: Test = {
        id: this.testId,
        ...this.testForm.value,
        questions: [] // Assuming no questions are added/updated in this form
      };
      this.testService.modifierTest(updatedTest,updatedTest.id).subscribe(
        response => {
          console.log('Test updated', response);
          this.router.navigate(['/testoffers/display']);
        },
        error => {
          console.error('Failed to update test', error);
        }
      );
    }
  }
}
