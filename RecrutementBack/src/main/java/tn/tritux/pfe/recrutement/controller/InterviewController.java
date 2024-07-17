package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tritux.pfe.recrutement.dto.request.InterviewRequest;
import tn.tritux.pfe.recrutement.dto.response.InterviewResponse;
import tn.tritux.pfe.recrutement.service.InterviewService;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<InterviewResponse> ajouterInterview(@RequestBody InterviewRequest interviewRequest) {
        return ResponseEntity.ok(interviewService.ajouterInterview(interviewRequest));
    }

    @PutMapping
    public ResponseEntity<InterviewResponse> modifierInterview(@RequestBody InterviewRequest interviewRequest) {
        return ResponseEntity.ok(interviewService.modifierInterview(interviewRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerInterview(@PathVariable Long id) {
        interviewService.supprimerInterview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewResponse> afficherInterviewParId(@PathVariable Long id) {
        return ResponseEntity.ok(interviewService.afficherInterviewParId(id));
    }

    @GetMapping
    public ResponseEntity<List<InterviewResponse>> afficherInterviews() {
        return ResponseEntity.ok(interviewService.afficherInterviews());
    }
}
