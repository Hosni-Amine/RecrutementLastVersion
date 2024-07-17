import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TestType } from 'src/app/models/test.model';
import { TestService } from 'src/app/service/test.service';

@Component({
  selector: 'app-addtestoffers',
  templateUrl: './addtestoffers.component.html',
  styleUrls: ['./addtestoffers.component.scss']
})
export class AddtestoffersComponent implements OnInit{
  testForm: FormGroup;
  testTypes = Object.values(TestType);

  constructor(
    private fb: FormBuilder,
    private testService: TestService,
    private router: Router
  ) {
    this.testForm = this.fb.group({
      type: ['', Validators.required],
      technologie: ['', Validators.required],
    });
  }

  ngOnInit() {}

  onSubmit() {
    if (this.testForm.valid) {
      const newTest = {
        ...this.testForm.value,
        dateCreation: new Date(this.testForm.value.dateCreation)
      };
      this.testService.ajouterTest(newTest).subscribe(
        (response) => {
          console.log('Test added', response);
          this.router.navigate(['/testoffers/display']);
        },
        (error) => {
          console.error('Failed to add test', error);
        }
      );
    }
  }
}
