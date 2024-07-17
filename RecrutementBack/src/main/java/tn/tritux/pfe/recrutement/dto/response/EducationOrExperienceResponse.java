package tn.tritux.pfe.recrutement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationOrExperienceResponse {

    private Long id;
    private String title;
    private String name;
    private String year;
    private String description;
    private String educationOrExperienceType;
    private Long profileId;

}

