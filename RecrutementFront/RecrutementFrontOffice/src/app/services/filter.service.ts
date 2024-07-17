import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FilterService {
  private jobTypeSubject = new BehaviorSubject<string>('');
  jobType$ = this.jobTypeSubject.asObservable();
  setJobs(jobType: string){
    this.jobTypeSubject.next(jobType);
  }

}
