package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tritux.pfe.recrutement.dto.request.ProfileRequest;
import tn.tritux.pfe.recrutement.dto.response.ProfileResponse;
import tn.tritux.pfe.recrutement.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileResponse> ajouterProfile(@RequestBody ProfileRequest profileRequest) {
        return ResponseEntity.ok(profileService.ajouterProfile(profileRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponse> modifierProfile(@RequestBody ProfileRequest profileRequest,@PathVariable Long id) {
        return ResponseEntity.ok(profileService.modifierProfile(profileRequest,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerProfile(@PathVariable Long id) {
        profileService.supprimerProfile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> afficherProfileParId(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.afficherProfileParId(id));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<ProfileResponse> afficherProfileParUserId(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.afficherProfileParUserId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponse>> afficherProfiles() {
        return ResponseEntity.ok(profileService.afficherProfiles());
    }
}
