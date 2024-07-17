import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Profile } from '../models/profile';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private apiUrl = 'http://localhost:8082/api/profiles';

  constructor(private http: HttpClient) {}

  ajouterProfile(profile: Profile): Observable<Profile> {
    return this.http.post<Profile>(this.apiUrl, profile);
  }

  modifierProfile(profile: Profile,id:number): Observable<Profile> {
    return this.http.put<Profile>(`${this.apiUrl}/${id}`, profile);
  }

  supprimerProfile(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  afficherProfileParId(id: number): Observable<Profile> {
    return this.http.get<Profile>(`${this.apiUrl}/${id}`);
  }
  afficherProfileParUserId(id: number): Observable<Profile> {
    return this.http.get<Profile>(`${this.apiUrl}/user/${id}`);
  }

  afficherProfiles(): Observable<Profile[]> {
    return this.http.get<Profile[]>(this.apiUrl);
  }
}
