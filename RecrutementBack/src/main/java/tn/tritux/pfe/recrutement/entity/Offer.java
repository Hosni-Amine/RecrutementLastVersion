package tn.tritux.pfe.recrutement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private String qualification;
    private String avantages;
    private String localisation;
    private String niveauExperience;
    private Integer nombrePostes;
    private String exigencesLangue;
    private String typeEmploi;
    private String image;
    private Date dateDePublication;
    private Date dateLimite;
    @Enumerated(EnumType.STRING)
    private EtatOffer etatOffer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categorie categorie;

}
