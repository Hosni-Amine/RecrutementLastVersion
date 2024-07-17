import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profile } from '../models/profile.model';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private apiUrl = 'http://localhost:8082/api/profiles';

  constructor(private http: HttpClient) {}

  ajouterProfile(profile: Profile): Observable<Profile> {
    return this.http.post<Profile>(`${this.apiUrl}`, profile);
  }

  modifierProfile(profile: Profile): Observable<Profile> {
    return this.http.put<Profile>(`${this.apiUrl}`, profile);
  }

  supprimerProfile(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  afficherProfileParId(id: number): Observable<Profile> {
    return this.http.get<Profile>(`${this.apiUrl}/${id}`);
  }

  afficherProfiles(): Observable<Profile[]> {
    return this.http.get<Profile[]>(`${this.apiUrl}`);
  }
}
