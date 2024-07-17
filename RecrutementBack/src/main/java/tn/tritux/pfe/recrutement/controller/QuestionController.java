package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tritux.pfe.recrutement.dto.request.QuestionRequest;
import tn.tritux.pfe.recrutement.dto.response.QuestionResponse;
import tn.tritux.pfe.recrutement.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse> ajouterQuestion(@RequestBody QuestionRequest questionRequest) {
        return ResponseEntity.ok(questionService.ajouterQuestion(questionRequest));
    }

    @PutMapping
    public ResponseEntity<QuestionResponse> modifierQuestion(@RequestBody QuestionRequest questionRequest) {
        return ResponseEntity.ok(questionService.modifierQuestion(questionRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerQuestion(@PathVariable Long id) {
        questionService.supprimerQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> afficherQuestionParId(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.afficherQuestionParId(id));
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> afficherQuestions() {
        return ResponseEntity.ok(questionService.afficherQuestions());
    }
}
