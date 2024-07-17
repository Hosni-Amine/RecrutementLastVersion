package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tritux.pfe.recrutement.dto.request.CategorieRequest;
import tn.tritux.pfe.recrutement.dto.response.CategorieResponse;
import tn.tritux.pfe.recrutement.service.CategorieService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class CategorieController {

    private final CategorieService categorieService;

    @PostMapping
    public ResponseEntity<CategorieResponse> ajouterCategorie(@RequestBody CategorieRequest categorieRequest) {
        return ResponseEntity.ok(categorieService.ajouterCategorie(categorieRequest));
    }

    @PutMapping
    public ResponseEntity<CategorieResponse> modifierCategorie(@RequestBody CategorieRequest categorieRequest) {
        return ResponseEntity.ok(categorieService.modifierCategorie(categorieRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCategorie(@PathVariable Long id) {
        categorieService.supprimerCategorie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieResponse> afficherCategorieParId(@PathVariable Long id) {
        return ResponseEntity.ok(categorieService.afficherCategorieParId(id));
    }

    @GetMapping
    public ResponseEntity<List<CategorieResponse>> afficherCategorie() {
        return ResponseEntity.ok(categorieService.afficherCategorie());
    }
}
