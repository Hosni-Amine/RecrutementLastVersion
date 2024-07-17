package tn.tritux.pfe.recrutement.service;

import tn.tritux.pfe.recrutement.dto.request.ProfileRequest;
import tn.tritux.pfe.recrutement.dto.response.ProfileResponse;

import java.util.List;

public interface ProfileService {
    ProfileResponse ajouterProfile(ProfileRequest profileRequest);
    ProfileResponse modifierProfile(ProfileRequest profileRequest,Long id);
    void supprimerProfile(Long id);
    ProfileResponse afficherProfileParId(Long id);
    ProfileResponse afficherProfileParUserId(Long id);
    List<ProfileResponse> afficherProfiles();
}
