import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../models/question.model';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private apiUrl = 'http://localhost:8082/api/questions';

  constructor(private http: HttpClient) {}

  ajouterQuestion(question: Question): Observable<Question> {
    return this.http.post<Question>(`${this.apiUrl}`, question);
  }

  modifierQuestion(question: Question): Observable<Question> {
    return this.http.put<Question>(`${this.apiUrl}`, question);
  }

  supprimerQuestion(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  afficherQuestionParId(id: number): Observable<Question> {
    return this.http.get<Question>(`${this.apiUrl}/${id}`);
  }

  afficherQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.apiUrl}`);
  }
}
