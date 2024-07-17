package tn.tritux.pfe.recrutement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateMeet;
    private String lienMeet;
    private String commentaire;
    private double score;
    private LocalDateTime dateCreation;
    @ManyToOne
    private Candidature candidature;
    @ManyToOne
    private User user;//recruteur
}
