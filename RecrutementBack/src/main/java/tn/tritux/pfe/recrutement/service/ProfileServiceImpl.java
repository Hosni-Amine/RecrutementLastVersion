package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.dto.request.ProfileRequest;
import tn.tritux.pfe.recrutement.dto.response.ProfileResponse;
import tn.tritux.pfe.recrutement.entity.Profile;
import tn.tritux.pfe.recrutement.entity.User;
import tn.tritux.pfe.recrutement.repository.ProfileRepository;
import tn.tritux.pfe.recrutement.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public ProfileResponse ajouterProfile(ProfileRequest profileRequest) {
        Profile profile = requestToEntity(profileRequest);
        profile = profileRepository.save(profile);
        return entityToResponse(profile);
    }

    @Override
    public ProfileResponse modifierProfile(ProfileRequest profileRequest,Long id) {
        Profile profile = requestToEntity(profileRequest);
        profile.setId(id);
        profile = profileRepository.save(profile);
        return entityToResponse(profile);
    }

    @Override
    public void supprimerProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public ProfileResponse afficherProfileParId(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return entityToResponse(profile);
    }
    public ProfileResponse afficherProfileParUserId(Long id){
        Profile profile=profileRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return entityToResponse(profile);
    }
    @Override
    public List<ProfileResponse> afficherProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    private Profile requestToEntity(ProfileRequest profileRequest) {
        Profile profile = new Profile();
        profile.setNumTel(profileRequest.getNumTel());
        profile.setAddress(profileRequest.getAddress());
        profile.setDateNaissance(profileRequest.getDateNaissance());
        profile.setCv(profileRequest.getCv());
        profile.setDiplome(profileRequest.getDiplome());
        profile.setLinkedinProfile(profileRequest.getLinkedinProfile());
        profile.setGithubProfile(profileRequest.getGithubProfile());
        profile.setBio(profileRequest.getBio());

        User user = userRepository.findById(profileRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(user);

        return profile;
    }

    private ProfileResponse entityToResponse(Profile profile) {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(profile.getId());
        profileResponse.setNumTel(profile.getNumTel());
        profileResponse.setAddress(profile.getAddress());
        profileResponse.setDateNaissance(profile.getDateNaissance());
        profileResponse.setCv(profile.getCv());
        profileResponse.setDiplome(profile.getDiplome());
        profileResponse.setLinkedinProfile(profile.getLinkedinProfile());
        profileResponse.setGithubProfile(profile.getGithubProfile());
        profileResponse.setBio(profile.getBio());
        profileResponse.setUserId(profile.getUser().getId());
        return profileResponse;
    }
}
