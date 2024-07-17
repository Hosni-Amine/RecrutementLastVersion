package tn.tritux.pfe.recrutement.service;

import tn.tritux.pfe.recrutement.dto.request.TestResultRequest;
import tn.tritux.pfe.recrutement.dto.response.TestResultResponse;

import java.util.List;

public interface TestResultService {
    TestResultResponse ajouterTestResult(TestResultRequest testResultRequest);
    TestResultResponse modifierTestResult(TestResultRequest testResultRequest);
    void supprimerTestResult(Long id);
    TestResultResponse afficherTestResultParId(Long id);
    List<TestResultResponse> afficherTestResults();
}
