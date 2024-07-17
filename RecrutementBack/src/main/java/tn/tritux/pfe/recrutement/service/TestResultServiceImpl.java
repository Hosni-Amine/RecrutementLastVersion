package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.dto.request.TestResultRequest;
import tn.tritux.pfe.recrutement.dto.response.TestResultResponse;
import tn.tritux.pfe.recrutement.entity.TestResult;
import tn.tritux.pfe.recrutement.repository.CandidatureRepository;
import tn.tritux.pfe.recrutement.repository.TestRepository;
import tn.tritux.pfe.recrutement.repository.TestResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestResultServiceImpl implements TestResultService {
    private final TestResultRepository testResultRepository;
    private final TestRepository testRepository;
    private final CandidatureRepository candidatureRepository;

    @Override
    public TestResultResponse ajouterTestResult(TestResultRequest testResultRequest) {
        TestResult testResult = requestToEntity(testResultRequest);
        testResult = testResultRepository.save(testResult);
        return entityToResponse(testResult);
    }

    @Override
    public TestResultResponse modifierTestResult(TestResultRequest testResultRequest) {
        TestResult testResult = requestToEntity(testResultRequest);
        testResult = testResultRepository.save(testResult);
        return entityToResponse(testResult);
    }

    @Override
    public void supprimerTestResult(Long id) {
        testResultRepository.deleteById(id);
    }

    @Override
    public TestResultResponse afficherTestResultParId(Long id) {
        TestResult testResult = testResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TestResult not found"));
        return entityToResponse(testResult);
    }

    @Override
    public List<TestResultResponse> afficherTestResults() {
        List<TestResult> testResults = testResultRepository.findAll();
        return testResults.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    private TestResult requestToEntity(TestResultRequest testResultRequest) {
        TestResult testResult = new TestResult();
        testResult.setNoteTotal(testResultRequest.getNoteTotal());
        testResult.setDate(testResultRequest.getDate());
        testResult.setTest(testRepository.findById(testResultRequest.getTestId()).orElseThrow(()->new RuntimeException("Test not found")));
        testResult.setCandidature(candidatureRepository.findById(testResultRequest.getCandidatureId()).orElseThrow(()->new RuntimeException("Candidature not found")));
        return testResult;
    }

    private TestResultResponse entityToResponse(TestResult testResult) {
        TestResultResponse testResultResponse = new TestResultResponse();
        testResultResponse.setId(testResult.getId());
        testResultResponse.setNoteTotal(testResult.getNoteTotal());
        testResultResponse.setDate(testResult.getDate());
        testResultResponse.setCandidatureId(testResult.getCandidature().getId());
        testResultResponse.setTestId(testResult.getTest().getId());
        return testResultResponse;
    }
}
