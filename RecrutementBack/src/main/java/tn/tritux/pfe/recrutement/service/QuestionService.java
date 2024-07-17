package tn.tritux.pfe.recrutement.service;

import tn.tritux.pfe.recrutement.dto.request.QuestionRequest;
import tn.tritux.pfe.recrutement.dto.response.QuestionResponse;

import java.util.List;

public interface QuestionService {
    QuestionResponse ajouterQuestion(QuestionRequest questionRequest);
    QuestionResponse modifierQuestion(QuestionRequest questionRequest);
    void supprimerQuestion(Long id);
    QuestionResponse afficherQuestionParId(Long id);
    List<QuestionResponse> afficherQuestions();
}
