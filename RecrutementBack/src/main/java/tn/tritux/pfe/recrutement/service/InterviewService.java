package tn.tritux.pfe.recrutement.service;

import tn.tritux.pfe.recrutement.dto.request.InterviewRequest;
import tn.tritux.pfe.recrutement.dto.response.InterviewResponse;

import java.util.List;

public interface InterviewService {
    InterviewResponse ajouterInterview(InterviewRequest interviewRequest);
    InterviewResponse modifierInterview(InterviewRequest interviewRequest);
    void supprimerInterview(Long id);
    InterviewResponse afficherInterviewParId(Long id);
    List<InterviewResponse> afficherInterviews();
}
