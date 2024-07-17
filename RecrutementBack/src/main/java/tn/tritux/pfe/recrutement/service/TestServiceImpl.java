package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.dto.request.TestRequest;
import tn.tritux.pfe.recrutement.dto.response.QuestionResponse;
import tn.tritux.pfe.recrutement.dto.response.TestResponse;
import tn.tritux.pfe.recrutement.entity.Question;
import tn.tritux.pfe.recrutement.entity.Test;
import tn.tritux.pfe.recrutement.entity.TestType;
import tn.tritux.pfe.recrutement.repository.TestRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;

    @Override
    public TestResponse ajouterTest(TestRequest testRequest) {
        Test test = requestToEntity(testRequest);
        test = testRepository.save(test);
        return entityToResponse(test);
    }

    @Override
    public TestResponse modifierTest(TestRequest testRequest,Long id) {
        Test test = requestToEntity(testRequest);
        test.setId(id);
        test = testRepository.save(test);
        return entityToResponse(test);
    }

    @Override
    public void supprimerTest(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public TestResponse afficherTestParId(Long id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));
        return entityToResponse(test);
    }

    @Override
    public List<TestResponse> afficherTests() {
        List<Test> tests = testRepository.findAll();
        return tests.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    private Test requestToEntity(TestRequest testRequest) {
        Test test = new Test();
        test.setDateCreation(LocalDateTime.now());
        test.setType(TestType.valueOf(testRequest.getType()));
        test.setTechnologie(testRequest.getTechnologie());
        return test;
    }

    private TestResponse entityToResponse(Test test) {
        TestResponse testResponse = new TestResponse();
        testResponse.setId(test.getId());
        testResponse.setDateCreation(test.getDateCreation());
        testResponse.setType(test.getType().name());
        testResponse.setTechnologie(test.getTechnologie());
        if(test.getQuestions()!=null){
            testResponse.setQuestions(test.getQuestions().stream()
                    .map(this::questionToResponse)
                    .collect(Collectors.toList()));
        }

        return testResponse;
    }

    private QuestionResponse questionToResponse(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .options(question.getOptions())
                .reponseCorrecte(question.getReponseCorrecte())
                .note(question.getNote())
                .build();
    }
}
