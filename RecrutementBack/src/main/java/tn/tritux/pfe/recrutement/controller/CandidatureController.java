package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tritux.pfe.recrutement.dto.request.CandidatureRequest;
import tn.tritux.pfe.recrutement.dto.request.OfferRequest;
import tn.tritux.pfe.recrutement.dto.response.CandidatureResponse;
import tn.tritux.pfe.recrutement.dto.response.OfferResponse;
import tn.tritux.pfe.recrutement.service.CandidatureService;

import java.util.List;

@RestController
@RequestMapping("/api/candidatures")
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:4200")
public class CandidatureController {
    private final CandidatureService candidatureService;
    @PostMapping
    public ResponseEntity<CandidatureResponse> addCandidat(@RequestBody CandidatureRequest candidatureRequest){
        CandidatureResponse candidatureResponse=candidatureService.ajouterCandidature(candidatureRequest);
        return ResponseEntity.ok(candidatureResponse);

    }
    @GetMapping("/{id}")
    public ResponseEntity<CandidatureResponse> getCandidatureById(@PathVariable Long id){
        CandidatureResponse candidatureResponse=candidatureService.afficherCandidatureParId(id);
        return ResponseEntity.ok(candidatureResponse);
    }
    @GetMapping("/offer/{id}")
    public ResponseEntity<List<CandidatureResponse>> getCandidatureByIdOffer(@PathVariable Long id){
        List<CandidatureResponse> candidatureResponses=candidatureService.afficherCandidatureParIdOffer(id);
        return ResponseEntity.ok(candidatureResponses);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidature(@PathVariable Long id){
        candidatureService.supprimerCandidature(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<CandidatureResponse>> getAllCandidatures(){
        List<CandidatureResponse> candidatureResponses=candidatureService.afficherCandidature();
        return ResponseEntity.ok(candidatureResponses);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CandidatureResponse> updateCandidat(@RequestBody CandidatureRequest candidatureRequest,@PathVariable Long id){
        CandidatureResponse candidatureResponse=candidatureService.modifierCandidature(candidatureRequest,id);
        return ResponseEntity.ok(candidatureResponse);

    }
}
