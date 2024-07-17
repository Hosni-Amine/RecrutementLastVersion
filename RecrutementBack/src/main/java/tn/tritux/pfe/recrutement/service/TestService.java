package tn.tritux.pfe.recrutement.service;

import tn.tritux.pfe.recrutement.dto.request.TestRequest;
import tn.tritux.pfe.recrutement.dto.response.TestResponse;

import java.util.List;

public interface TestService {
    TestResponse ajouterTest(TestRequest testRequest);
    TestResponse modifierTest(TestRequest testRequest,Long id);
    void supprimerTest(Long id);
    TestResponse afficherTestParId(Long id);
    List<TestResponse> afficherTests();
}
