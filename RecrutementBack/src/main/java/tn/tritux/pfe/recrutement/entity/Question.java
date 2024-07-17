package tn.tritux.pfe.recrutement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @ElementCollection
    private List<String> options;
    private String reponseCorrecte;
    private double note;
    @ManyToOne
    private Test test;

}
