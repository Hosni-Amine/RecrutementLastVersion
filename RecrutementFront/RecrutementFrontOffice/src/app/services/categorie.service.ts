import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categorie } from '../models/categorie.model';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {
  private apiUrl='http://localhost:8082/api/categories';

  constructor(private http: HttpClient) {}

  ajouterCategorie(categorieRequest: Categorie): Observable<Categorie> {
    return this.http.post<Categorie>(`${this.apiUrl}`, categorieRequest);
  }

  modifierCategorie(categorieRequest: Categorie): Observable<Categorie> {
    return this.http.put<Categorie>(`${this.apiUrl}`, categorieRequest);
  }

  supprimerCategorie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  afficherCategorieParId(id: number): Observable<Categorie> {
    return this.http.get<Categorie>(`${this.apiUrl}/${id}`);
  }

  afficherCategorie(): Observable<Categorie[]> {
    return this.http.get<Categorie[]>(`${this.apiUrl}`);
  }
}
