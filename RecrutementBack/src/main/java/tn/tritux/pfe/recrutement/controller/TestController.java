package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tritux.pfe.recrutement.dto.request.TestRequest;
import tn.tritux.pfe.recrutement.dto.response.TestResponse;
import tn.tritux.pfe.recrutement.service.TestService;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class TestController {

    private final TestService testService;

    @PostMapping
    public ResponseEntity<TestResponse> ajouterTest(@RequestBody TestRequest testRequest) {
        return ResponseEntity.ok(testService.ajouterTest(testRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestResponse> modifierTest(@RequestBody TestRequest testRequest,@PathVariable Long id) {
        return ResponseEntity.ok(testService.modifierTest(testRequest,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerTest(@PathVariable Long id) {
        testService.supprimerTest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResponse> afficherTestParId(@PathVariable Long id) {
        return ResponseEntity.ok(testService.afficherTestParId(id));
    }

    @GetMapping
    public ResponseEntity<List<TestResponse>> afficherTests() {
        return ResponseEntity.ok(testService.afficherTests());
    }
}
