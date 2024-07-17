import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Interview } from '../models/interview.model';

@Injectable({
  providedIn: 'root'
})
export class InterviewService {

  private apiUrl='http://localhost:8082/api/interviews';

  constructor(private http: HttpClient) {}

  ajouterInterview(interviewRequest: Interview): Observable<Interview> {
    return this.http.post<Interview>(`${this.apiUrl}`, interviewRequest);
  }

  modifierInterview(interviewRequest: Interview): Observable<Interview> {
    return this.http.put<Interview>(`${this.apiUrl}`, interviewRequest);
  }

  supprimerInterview(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  afficherInterviewParId(id: number): Observable<Interview> {
    return this.http.get<Interview>(`${this.apiUrl}/${id}`);
  }

  afficherInterviews(): Observable<Interview[]> {
    return this.http.get<Interview[]>(`${this.apiUrl}`);
  }
}
