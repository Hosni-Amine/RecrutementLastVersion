package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.dto.request.QuestionRequest;
import tn.tritux.pfe.recrutement.dto.response.QuestionResponse;
import tn.tritux.pfe.recrutement.entity.Question;
import tn.tritux.pfe.recrutement.entity.Test;
import tn.tritux.pfe.recrutement.repository.QuestionRepository;
import tn.tritux.pfe.recrutement.repository.TestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    @Override
    public QuestionResponse ajouterQuestion(QuestionRequest questionRequest) {
        Question question = requestToEntity(questionRequest);
        question = questionRepository.save(question);
        return entityToResponse(question);
    }

    @Override
    public QuestionResponse modifierQuestion(QuestionRequest questionRequest) {
        Question question = requestToEntity(questionRequest);

        question = questionRepository.save(question);
        return entityToResponse(question);
    }

    @Override
    public void supprimerQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionResponse afficherQuestionParId(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return entityToResponse(question);
    }

    @Override
    public List<QuestionResponse> afficherQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    private Question requestToEntity(QuestionRequest questionRequest) {
        Question question = new Question();
        question.setQuestion(questionRequest.getQuestion());
        question.setOptions(questionRequest.getOptions());
        question.setReponseCorrecte(questionRequest.getReponseCorrecte());
        question.setNote(questionRequest.getNote());


        Test test = testRepository.findById(questionRequest.getTestId())
                .orElseThrow(() -> new RuntimeException("Test not found"));
        question.setTest(test);

        return question;
    }

    private QuestionResponse entityToResponse(Question question) {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(question.getId());
        questionResponse.setQuestion(question.getQuestion());
        questionResponse.setOptions(question.getOptions());
        questionResponse.setReponseCorrecte(question.getReponseCorrecte());
        questionResponse.setNote(question.getNote());
        questionResponse.setTestId(question.getTest().getId());
        return questionResponse;
    }
}
