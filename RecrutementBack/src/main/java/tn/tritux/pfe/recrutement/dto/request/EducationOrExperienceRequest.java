package tn.tritux.pfe.recrutement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.tritux.pfe.recrutement.entity.EducationOrExperienceType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationOrExperienceRequest {
    private String title;
    private String name;
    private String year;
    private String description;
    private String educationOrExperienceType;
    private Long profileId;

}
