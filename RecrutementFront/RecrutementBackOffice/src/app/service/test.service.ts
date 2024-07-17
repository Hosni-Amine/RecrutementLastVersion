import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Test } from '../models/test.model';

@Injectable({
  providedIn: 'root'
})
export class TestService {
  private apiUrl = 'http://localhost:8082/api/tests';

  constructor(private http: HttpClient) {}

  ajouterTest(test: Test): Observable<Test> {
    return this.http.post<Test>(`${this.apiUrl}`, test);
  }

  modifierTest(test: Test,id:number): Observable<Test> {
    return this.http.put<Test>(`${this.apiUrl}/${id}`, test);
  }

  supprimerTest(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  afficherTestParId(id: number): Observable<Test> {
    return this.http.get<Test>(`${this.apiUrl}/${id}`);
  }

  afficherTests(): Observable<Test[]> {
    return this.http.get<Test[]>(`${this.apiUrl}`);
  }
}
