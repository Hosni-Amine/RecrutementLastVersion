import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from '../models/offer.model';

@Injectable({
  providedIn: 'root'
})
export class OfferService {
  private apiUrl = 'http://localhost:8082/api/offers';

  constructor(private http: HttpClient) {}

  ajouterOffer(offer: Offer): Observable<Offer> {
    return this.http.post<Offer>(`${this.apiUrl}`, offer);
  }

  modifierOffer(offer: Offer): Observable<Offer> {
    return this.http.put<Offer>(`${this.apiUrl}`, offer);
  }

  supprimerOffer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  afficherOfferParId(id: number): Observable<Offer> {
    return this.http.get<Offer>(`${this.apiUrl}/${id}`);
  }

  afficherOfferParCategorieId(idCategorie: number): Observable<Offer[]> {
    return this.http.get<Offer[]>(`${this.apiUrl}/categorie/${idCategorie}`);
  }

  afficherOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(`${this.apiUrl}`);
  }
}
