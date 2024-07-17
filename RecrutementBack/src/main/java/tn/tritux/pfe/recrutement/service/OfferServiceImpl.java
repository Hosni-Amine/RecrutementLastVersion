package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.dto.request.OfferRequest;
import tn.tritux.pfe.recrutement.dto.response.OfferResponse;
import tn.tritux.pfe.recrutement.entity.Candidature;
import tn.tritux.pfe.recrutement.entity.Categorie;
import tn.tritux.pfe.recrutement.entity.EtatOffer;
import tn.tritux.pfe.recrutement.entity.Offer;
import tn.tritux.pfe.recrutement.repository.CandidatureRepository;
import tn.tritux.pfe.recrutement.repository.CategorieRepository;
import tn.tritux.pfe.recrutement.repository.OfferRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;
    private final CandidatureRepository candidatureRepository;
    private final CategorieRepository categorieRepository;

    @Override
    public OfferResponse ajouterOffer(OfferRequest offerRequest) {
        Offer offer=requestToEntity(offerRequest);
        offer.setEtatOffer(EtatOffer.OUVERT);
        offer=offerRepository.save(offer);
        return entityToResponse(offer);
    }

    @Override
    public OfferResponse modifierOffer(OfferRequest offerRequest) {
        Offer offer=requestToEntity(offerRequest);
        offer=offerRepository.save(offer);
        return entityToResponse(offer);
    }

    @Override
    public OfferResponse afficherOfferParId(Long id) {
        Offer offer=offerRepository.findById(id).orElseThrow(()->new RuntimeException("Offer not found!"));
        return entityToResponse(offer);
    }

    @Override
    public void supprimerOfferParId(Long id) {
        List<Candidature> candidatures=candidatureRepository.findAllByOfferId(id);
        for(Candidature candidature:candidatures){
            candidatureRepository.delete(candidature);
        }
        offerRepository.deleteById(id);
    }

    @Override
    public List<OfferResponse> afficherOffer() {
        List<Offer> offers=offerRepository.findAll();
        /*List<OfferResponse> result=new ArrayList<>();
        for(Offer offer:offers){
            result.add(entityToResponse(offer));
        }*/
        return offers.stream().map(this::entityToResponse).collect(Collectors.toList());
    }
    @Override
    public List<OfferResponse> afficherOfferParCategorieId(Long id){
        Categorie categorie=categorieRepository.findById(id).orElseThrow(()->new RuntimeException("Categorie not found"));
        List<Offer> offers=offerRepository.findAllByCategorie(categorie);
        return offers.stream().map(this::entityToResponse).collect(Collectors.toList());
    }
    public List<OfferResponse> getOffersByKeyWord(String keyword){
        return offerRepository.findAll().stream()
                .filter(offer->matchesKeyword(offer,keyword))
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }
    private boolean matchesKeyword(Offer offer,String keyword){
        return Optional.ofNullable(keyword)
                .map(String::toLowerCase)
                .map(k->offerMatches(offer,k))
                .orElse(true);
    }
    private boolean offerMatches(Offer offer,String keyword){
        if(offer.getTitre().toLowerCase().contains(keyword)) return true;
        if(offer.getDescription().toLowerCase().contains(keyword)) return true;
        if(offer.getCategorie()!=null && offer.getCategorie().getNom().toLowerCase().contains(keyword)) return true;
        if(offer.getAvantages().toLowerCase().contains(keyword)) return true;
        if(offer.getQualification().toLowerCase().contains(keyword)) return true;
        if(offer.getLocalisation().toLowerCase().contains(keyword)) return true;
        if(offer.getNiveauExperience().toLowerCase().contains(keyword)) return true;
        if(offer.getExigencesLangue().toLowerCase().contains(keyword)) return true;
        if(offer.getTypeEmploi().toLowerCase().contains(keyword)) return true;
        if(offer.getDateLimite().toString().contains(keyword)) return true;
        return false;
    }
    private Offer requestToEntity(OfferRequest offerRequest){
        Offer offer=new Offer();
        //offer.setId(offerRequest.getId());
        offer.setDescription(offerRequest.getDescription());
        offer.setAvantages(offerRequest.getAvantages());
        offer.setLocalisation(offerRequest.getLocalisation());
        offer.setTitre(offerRequest.getTitre());
        offer.setDateLimite(offerRequest.getDateLimite());
        offer.setQualification(offerRequest.getQualification());
        offer.setExigencesLangue(offerRequest.getExigencesLangue());
        offer.setNombrePostes(offerRequest.getNombrePostes());
        offer.setDateDePublication(new Date());
        offer.setNiveauExperience(offerRequest.getNiveauExperience());
        offer.setTypeEmploi(offerRequest.getTypeEmploi());
        offer.setImage(offerRequest.getImage());
        offer.setEtatOffer(EtatOffer.valueOf(offerRequest.getEtatOffer()));
        offer.setCategorie(categorieRepository.findById(offerRequest.getCategorieId()).orElseThrow(()->new RuntimeException("Categorie not found")));

        return offer;
    }
    private OfferResponse entityToResponse(Offer offer){
        OfferResponse response=new OfferResponse();
        response.setId(offer.getId());
        response.setDescription(offer.getDescription());
        response.setAvantages(offer.getAvantages());
        response.setLocalisation(offer.getLocalisation());
        response.setTitre(offer.getTitre());
        response.setDateLimite(offer.getDateLimite());
        response.setQualification(offer.getQualification());
        response.setExigencesLangue(offer.getExigencesLangue());
        response.setNombrePostes(offer.getNombrePostes());
        response.setDateDePublication(offer.getDateDePublication());
        response.setNiveauExperience(offer.getNiveauExperience());
        response.setImage(offer.getImage());
        response.setTypeEmploi(offer.getTypeEmploi());
        response.setCategorieId(offer.getCategorie().getId());
        response.setCategorieName(offer.getCategorie().getNom());

        response.setEtatOffer(offer.getEtatOffer().name());
        return response;
    }
}
