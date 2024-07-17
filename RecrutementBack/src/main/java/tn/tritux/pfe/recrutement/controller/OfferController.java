package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tritux.pfe.recrutement.dto.request.OfferRequest;
import tn.tritux.pfe.recrutement.dto.response.OfferResponse;
import tn.tritux.pfe.recrutement.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<OfferResponse> ajouterOffer(@RequestBody OfferRequest offerRequest) {
        return ResponseEntity.ok(offerService.ajouterOffer(offerRequest));
    }

    @PutMapping
    public ResponseEntity<OfferResponse> modifierOffer(@RequestBody OfferRequest offerRequest) {
        return ResponseEntity.ok(offerService.modifierOffer(offerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerOfferParId(@PathVariable Long id) {
        offerService.supprimerOfferParId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponse> afficherOfferParId(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.afficherOfferParId(id));
    }

    @GetMapping("/categorie/{idCategorie}")
    public ResponseEntity<List<OfferResponse>> afficherOfferParCategorieId(@PathVariable Long idCategorie) {
        return ResponseEntity.ok(offerService.afficherOfferParCategorieId(idCategorie));
    }

    @GetMapping
    public ResponseEntity<List<OfferResponse>> afficherOffer() {
        return ResponseEntity.ok(offerService.afficherOffer());
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<OfferResponse>> afficherOfferByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(offerService.getOffersByKeyWord(keyword));
    }
}
