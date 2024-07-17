package tn.tritux.pfe.recrutement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numTel;
    private String address;
    private LocalDate dateNaissance;
    private String cv;
    private String diplome;
    private String linkedinProfile;
    private String githubProfile;
    private String bio;

    @OneToOne
    private User user;
}
