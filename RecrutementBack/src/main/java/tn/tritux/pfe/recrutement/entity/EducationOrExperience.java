package tn.tritux.pfe.recrutement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EducationOrExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String name;
    private String year;
    private String description;
    private EducationOrExperienceType educationOrExperienceType;
    @ManyToOne
    private Profile profile;
}
