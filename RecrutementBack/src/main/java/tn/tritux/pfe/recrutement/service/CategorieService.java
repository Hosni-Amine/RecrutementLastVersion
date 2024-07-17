package tn.tritux.pfe.recrutement.service;

import tn.tritux.pfe.recrutement.dto.request.CandidatureRequest;
import tn.tritux.pfe.recrutement.dto.request.CategorieRequest;
import tn.tritux.pfe.recrutement.dto.response.CandidatureResponse;
import tn.tritux.pfe.recrutement.dto.response.CategorieResponse;

import java.util.List;

public interface CategorieService {
    CategorieResponse ajouterCategorie(CategorieRequest candidatureRequest);

    CategorieResponse modifierCategorie(CategorieRequest candidatureRequest);
    void supprimerCategorie(Long id);
    CategorieResponse afficherCategorieParId(Long id);
    List<CategorieResponse> afficherCategorie();
}
