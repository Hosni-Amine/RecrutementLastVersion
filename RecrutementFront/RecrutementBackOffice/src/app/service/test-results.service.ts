import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TestResult } from '../models/test-result.model';

@Injectable({
  providedIn: 'root'
})
export class TestResultService {
  private apiUrl = 'http://localhost:8082/api/test-results';

  constructor(private http: HttpClient) {}

  ajouterTestResult(testResult: TestResult): Observable<TestResult> {
    return this.http.post<TestResult>(`${this.apiUrl}`, testResult);
  }

  modifierTestResult(testResult: TestResult): Observable<TestResult> {
    return this.http.put<TestResult>(`${this.apiUrl}`, testResult);
  }

  supprimerTestResult(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  afficherTestResultParId(id: number): Observable<TestResult> {
    return this.http.get<TestResult>(`${this.apiUrl}/${id}`);
  }

  afficherTestResults(): Observable<TestResult[]> {
    return this.http.get<TestResult[]>(`${this.apiUrl}`);
  }
}
