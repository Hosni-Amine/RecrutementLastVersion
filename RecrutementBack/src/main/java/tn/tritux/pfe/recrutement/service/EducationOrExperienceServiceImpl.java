package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.dto.request.EducationOrExperienceRequest;
import tn.tritux.pfe.recrutement.dto.response.EducationOrExperienceResponse;
import tn.tritux.pfe.recrutement.entity.EducationOrExperience;
import tn.tritux.pfe.recrutement.entity.Profile;
import tn.tritux.pfe.recrutement.repository.EducationOrExperienceRepository;
import tn.tritux.pfe.recrutement.repository.ProfileRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class EducationOrExperienceServiceImpl implements EducationOrExperienceService {

    //private final CategorieRepository categorieRepository;
    private final EducationOrExperienceRepository educationOrExperienceRepository;
    private final ProfileRepository profileRepository;


    @Override
    public EducationOrExperienceResponse ajouterEducationOrExperience(EducationOrExperienceRequest educationOrExperienceRequest) {
        /*

        Categorie categorie = requestToEntity(categorieRequest);
        categorie = categorieRepository.save(categorie);
        return entityToResponse(categorie);
    }
         */

       // EducationOrExperience educationOrExperience=
        return null;
    }

    @Override
    public EducationOrExperienceResponse modifierEducationOrExperience(EducationOrExperienceRequest educationOrExperienceRequest) {
        return null;
    }

    @Override
    public void supprimerEducationOrExperience(Long id) {

    }

    @Override
    public EducationOrExperienceResponse afficherEducationOrExperienceResponseParId(Long id) {
        return null;
    }

    @Override
    public List<EducationOrExperienceResponse> afficherEducationOrExperienceResponse() {
        return null;
    }



    private EducationOrExperience requestToEntity(EducationOrExperienceRequest educationOrExperienceRequest){
        EducationOrExperience educationOrExperience = new EducationOrExperience();
        educationOrExperience.setTitle(educationOrExperienceRequest.getTitle());
        educationOrExperience.setName(educationOrExperienceRequest.getName());
        educationOrExperience.setYear(educationOrExperienceRequest.getYear());
        Profile profile=profileRepository.findById(educationOrExperienceRequest.getProfileId()).orElseThrow(()->new RuntimeException("Profile not found"));
        return educationOrExperience;
    }

    private EducationOrExperienceResponse entityToResponse(EducationOrExperience educationOrExperience){

   EducationOrExperienceResponse educationOrExperienceResponse = new EducationOrExperienceResponse();
    educationOrExperienceResponse.setId(educationOrExperience.getId());
    educationOrExperienceResponse.setTitle(educationOrExperience.getTitle());
        educationOrExperienceResponse.setName(educationOrExperience.getName());
        educationOrExperienceResponse.setYear(educationOrExperience.getYear());
        educationOrExperienceResponse.setProfileId(educationOrExperience.getProfile().getId());
        return educationOrExperienceResponse;


    }
}






