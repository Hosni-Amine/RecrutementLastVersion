package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.dto.request.CandidatureRequest;
import tn.tritux.pfe.recrutement.dto.response.CandidatureResponse;
import tn.tritux.pfe.recrutement.entity.*;
import tn.tritux.pfe.recrutement.repository.CandidatureRepository;
import tn.tritux.pfe.recrutement.repository.OfferRepository;
import tn.tritux.pfe.recrutement.repository.ProfileRepository;
import tn.tritux.pfe.recrutement.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidatureServiceImpl implements CandidatureService{

    private final CandidatureRepository candidatureRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final ProfileRepository profileRepository;
    @Override
    public CandidatureResponse ajouterCandidature(CandidatureRequest candidatureRequest) {
        Candidature candidature=requestToEntity(candidatureRequest);
        candidature=candidatureRepository.save(candidature);
        return entityToResponse(candidature);
    }

    @Override
    public CandidatureResponse modifierCandidature(CandidatureRequest candidatureRequest,Long id) {
        Candidature candidature=candidatureRepository.findById(id).orElseThrow(()->new RuntimeException("Candidature not found"));
        candidature.setEtatCandidature(EtatCandidature.valueOf(candidatureRequest.getEtat()));
        candidature.setDateCandidature(candidature.getDateCandidature());
        candidature=candidatureRepository.save(candidature);
        return entityToResponse(candidature);
    }

    @Override
    public void supprimerCandidature(Long id) {
        candidatureRepository.deleteById(id);
    }

    @Override
    public CandidatureResponse afficherCandidatureParId(Long id) {
        Candidature candidature=candidatureRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Candidature not found"));
        return entityToResponse(candidature);
    }
    @Override
    public List<CandidatureResponse> afficherCandidatureParIdOffer(Long idOffer) {
        List<Candidature> candidatures=candidatureRepository.findAll()
                .stream().filter(c->c.getOffer().getId().equals(idOffer)).toList();
        return candidatures.stream().map(this::entityToResponse).toList();
    }

    @Override
    public List<CandidatureResponse> afficherCandidature() {
        List<Candidature> candidatures=candidatureRepository.findAll();
        return candidatures.stream().map(this::entityToResponse).collect(Collectors.toList());
    }
    private Candidature requestToEntity(CandidatureRequest candidatureRequest){
        Candidature candidature=new Candidature();
        candidature.setDateCandidature(new Date());
        candidature.setEtatCandidature(EtatCandidature.valueOf(candidatureRequest.getEtat()));

        Profile candidat=profileRepository.findById(candidatureRequest.getProfileId())
                .orElseThrow(()->new RuntimeException("User not found"));

        candidature.setProfile(candidat);
        Offer offer=offerRepository.findById(candidatureRequest.getIdOffer())
                .orElseThrow(()->new RuntimeException("Offer not found"));
        candidature.setOffer(offer);
        return candidature;
    }
    private CandidatureResponse entityToResponse(Candidature candidature){
        CandidatureResponse candidatureResponse=new CandidatureResponse();
        candidatureResponse.setDateCandidature(candidature.getDateCandidature());

        candidatureResponse.setEtat(candidature.getEtatCandidature().name());

        candidatureResponse.setProfileId(candidature.getProfile().getId());
        candidatureResponse.setOfferId(candidature.getOffer().getId());
        candidatureResponse.setId(candidature.getId());


        return candidatureResponse;
    }
}
