package tn.tritux.pfe.recrutement.service;

import tn.tritux.pfe.recrutement.dto.request.CandidatureRequest;
import tn.tritux.pfe.recrutement.dto.response.CandidatureResponse;

import java.util.List;

public interface CandidatureService {

    CandidatureResponse ajouterCandidature(CandidatureRequest candidatureRequest);

    CandidatureResponse modifierCandidature(CandidatureRequest candidatureRequest,Long id);
    void supprimerCandidature(Long id);
    CandidatureResponse afficherCandidatureParId(Long id);
    List<CandidatureResponse> afficherCandidature();
    List<CandidatureResponse> afficherCandidatureParIdOffer(Long idOffer);


}
