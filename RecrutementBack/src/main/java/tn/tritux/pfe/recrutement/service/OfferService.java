package tn.tritux.pfe.recrutement.service;

import tn.tritux.pfe.recrutement.dto.request.OfferRequest;
import tn.tritux.pfe.recrutement.dto.response.OfferResponse;

import java.util.List;

public interface OfferService {
    OfferResponse ajouterOffer(OfferRequest offerRequest);
    OfferResponse modifierOffer(OfferRequest offerRequest);
    OfferResponse afficherOfferParId(Long id);
    void supprimerOfferParId(Long id);
    List<OfferResponse> afficherOffer();
    List<OfferResponse> afficherOfferParCategorieId(Long id);
    List<OfferResponse> getOffersByKeyWord(String keyword);
}
