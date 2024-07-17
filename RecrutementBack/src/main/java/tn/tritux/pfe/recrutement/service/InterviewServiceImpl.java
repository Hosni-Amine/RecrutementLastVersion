package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.dto.request.InterviewRequest;
import tn.tritux.pfe.recrutement.dto.response.InterviewResponse;
import tn.tritux.pfe.recrutement.entity.Interview;
import tn.tritux.pfe.recrutement.repository.CandidatureRepository;
import tn.tritux.pfe.recrutement.repository.InterviewRepository;
import tn.tritux.pfe.recrutement.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InterviewServiceImpl implements InterviewService {
    private final InterviewRepository interviewRepository;
    private final CandidatureRepository candidatureRepository;
    private final UserRepository userRepository;

    @Override
    public InterviewResponse ajouterInterview(InterviewRequest interviewRequest) {
        Interview interview = requestToEntity(interviewRequest);
        interview = interviewRepository.save(interview);
        return entityToResponse(interview);
    }

    @Override
    public InterviewResponse modifierInterview(InterviewRequest interviewRequest) {
        Interview interview = requestToEntity(interviewRequest);
        interview = interviewRepository.save(interview);
        return entityToResponse(interview);
    }

    @Override
    public void supprimerInterview(Long id) {
        interviewRepository.deleteById(id);
    }

    @Override
    public InterviewResponse afficherInterviewParId(Long id) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        return entityToResponse(interview);
    }

    @Override
    public List<InterviewResponse> afficherInterviews() {
        List<Interview> interviews = interviewRepository.findAll();
        return interviews.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    private Interview requestToEntity(InterviewRequest interviewRequest) {
        Interview interview = new Interview();
        interview.setId(interviewRequest.getId());
        interview.setDateMeet(interviewRequest.getDateMeet());
        interview.setLienMeet(interviewRequest.getLienMeet());
        interview.setCommentaire(interviewRequest.getCommentaire());
        interview.setScore(interviewRequest.getScore());
        interview.setDateCreation(interviewRequest.getDateCreation());
        interview.setCandidature(candidatureRepository.findById(interviewRequest.getCandidatureId()).orElseThrow(()->new RuntimeException("Candidature not found")));
        interview.setUser(userRepository.findById(interviewRequest.getRecruteurId()).orElse(null));
        return interview;
    }

    private InterviewResponse entityToResponse(Interview interview) {
        InterviewResponse interviewResponse = new InterviewResponse();
        interviewResponse.setId(interview.getId());
        interviewResponse.setDateMeet(interview.getDateMeet());
        interviewResponse.setLienMeet(interview.getLienMeet());
        interviewResponse.setCommentaire(interview.getCommentaire());
        interviewResponse.setScore(interview.getScore());
        interviewResponse.setDateCreation(interview.getDateCreation());
        interviewResponse.setCandidatureId(interview.getCandidature().getId());
        interviewResponse.setRecruteurId(interview.getUser().getId());
        return interviewResponse;
    }
}
