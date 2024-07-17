import { Component, OnInit } from '@angular/core';
import { Candidature } from '../models/candidature.model';
import { CandidatureService } from '../service/candidature.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-candidature',
  templateUrl: './candidature.component.html',
  styleUrls: ['./candidature.component.scss']
})
export class CandidatureComponent implements OnInit {
  candidatures!: Candidature[]; // Liste des candidatures
  search: string = ''; // Terme de recherche

  constructor(private candidatureService: CandidatureService, private router: Router) { }

  ngOnInit(): void {
    this.loadCandidatures(); // Chargement des candidatures lors de l'initialisation du composant
  }

  // Méthode pour charger les candidatures depuis le service
  loadCandidatures() {
    this.candidatureService.afficherCandidature().subscribe(data => {
      this.candidatures = data; // Assignation des candidatures récupérées
    });
  }

  // Méthode pour supprimer une candidature
  deleteCandidature(id: number ) {
    if (id != null) {
      this.candidatureService.supprimerCandidature(id).subscribe(() => {
        this.loadCandidatures(); // Rechargement des candidatures après suppression
      });
    }
  }

  // Méthode pour mettre à jour une candidature
  updateCandidature(id: number ) {
    // Implémentez la logique pour mettre à jour une candidature
  }

  // Méthode pour naviguer vers la page d'ajout de candidature
  gotoAddCandidature() {
    this.router.navigate(['/candidature/add']);
  }

  // Méthode pour filtrer les candidatures en fonction du terme de recherche
  filterCandidatures() {
    // Implémentez la logique pour filtrer les candidatures
  }
}
