import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Candidature } from '../models/candidature.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CandidatureService {
  private apiUrl='http://localhost:8082/api/candidatures';

  constructor(private http: HttpClient) {}

  ajouterCandidature(candidatureRequest: Candidature): Observable<Candidature> {
    return this.http.post<Candidature>(`${this.apiUrl}`, candidatureRequest);
  }

  modifierCandidature(candidatureRequest: Candidature): Observable<Candidature> {
    return this.http.put<Candidature>(`${this.apiUrl}`, candidatureRequest);
  }

  supprimerCandidature(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  afficherCandidatureParId(id: number): Observable<Candidature> {
    return this.http.get<Candidature>(`${this.apiUrl}/${id}`);
  }

  afficherCandidatureParIdOffer(idOffer: number): Observable<Candidature[]> {
    return this.http.get<Candidature[]>(`${this.apiUrl}/offer/${idOffer}`);
  }

  afficherCandidature(): Observable<Candidature[]> {
    return this.http.get<Candidature[]>(`${this.apiUrl}`);
  }
}
