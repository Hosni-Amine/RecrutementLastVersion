package tn.tritux.pfe.recrutement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double noteTotal;
    private LocalDateTime date;
    @ManyToOne
    private Test test;
    @ManyToOne
    private Candidature candidature;
}
