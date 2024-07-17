package tn.tritux.pfe.recrutement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {
    private String numTel;
    private String address;
    private LocalDate dateNaissance;
    private String cv;
    private String diplome;
    private String linkedinProfile;
    private String githubProfile;
    private String bio;
    private Long userId;
}
