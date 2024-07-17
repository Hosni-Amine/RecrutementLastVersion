package tn.tritux.pfe.recrutement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreation;
    @Enumerated(EnumType.STRING)
    private TestType type;
    private String technologie;
    @OneToMany(mappedBy = "test")
    private List<Question> questions;
    @OneToMany(mappedBy = "test")
    private List<TestResult> testResults;

}
