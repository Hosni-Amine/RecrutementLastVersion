package tn.tritux.pfe.recrutement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String image;
    @OneToMany(mappedBy = "categorie")
    private List<Offer> offers;
}
