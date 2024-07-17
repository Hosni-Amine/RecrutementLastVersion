package tn.tritux.pfe.recrutement.service;

import tn.tritux.pfe.recrutement.dto.request.EducationOrExperienceRequest;
import tn.tritux.pfe.recrutement.dto.response.EducationOrExperienceResponse;

import java.util.List;

public interface EducationOrExperienceService {
    /*
    List<CategorieResponse> afficherCategorie();
     */

    EducationOrExperienceResponse ajouterEducationOrExperience(EducationOrExperienceRequest educationOrExperienceRequest);

    EducationOrExperienceResponse modifierEducationOrExperience(EducationOrExperienceRequest educationOrExperienceRequest);

    void supprimerEducationOrExperience(Long id);

    EducationOrExperienceResponse afficherEducationOrExperienceResponseParId(Long id);

    List<EducationOrExperienceResponse> afficherEducationOrExperienceResponse();

}
