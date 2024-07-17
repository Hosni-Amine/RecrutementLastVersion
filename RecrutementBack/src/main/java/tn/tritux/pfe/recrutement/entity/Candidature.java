package tn.tritux.pfe.recrutement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Profile profile;//profile candidat

    @ManyToOne
    private Offer offer;
    @Enumerated(EnumType.STRING)
    private EtatCandidature etatCandidature;
    private Date dateCandidature;
    @OneToMany(mappedBy = "candidature")
    private List<TestResult> testResults;
}
