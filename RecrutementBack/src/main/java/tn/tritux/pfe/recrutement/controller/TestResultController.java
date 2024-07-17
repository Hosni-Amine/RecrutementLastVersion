package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tritux.pfe.recrutement.dto.request.TestResultRequest;
import tn.tritux.pfe.recrutement.dto.response.TestResultResponse;
import tn.tritux.pfe.recrutement.service.TestResultService;

import java.util.List;

@RestController
@RequestMapping("/api/test-results")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class TestResultController {

    private final TestResultService testResultService;

    @PostMapping
    public ResponseEntity<TestResultResponse> ajouterTestResult(@RequestBody TestResultRequest testResultRequest) {
        return ResponseEntity.ok(testResultService.ajouterTestResult(testResultRequest));
    }

    @PutMapping
    public ResponseEntity<TestResultResponse> modifierTestResult(@RequestBody TestResultRequest testResultRequest) {
        return ResponseEntity.ok(testResultService.modifierTestResult(testResultRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerTestResult(@PathVariable Long id) {
        testResultService.supprimerTestResult(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResultResponse> afficherTestResultParId(@PathVariable Long id) {
        return ResponseEntity.ok(testResultService.afficherTestResultParId(id));
    }

    @GetMapping
    public ResponseEntity<List<TestResultResponse>> afficherTestResults() {
        return ResponseEntity.ok(testResultService.afficherTestResults());
    }
}
