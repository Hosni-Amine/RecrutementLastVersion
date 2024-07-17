package tn.tritux.pfe.recrutement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private Long id;
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
