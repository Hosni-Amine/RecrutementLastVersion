package tn.tritux.pfe.recrutement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterviewRequest {
    private Long id;
    private LocalDateTime dateMeet;
    private String lienMeet;
    private String commentaire;
    private double score;
    private LocalDateTime dateCreation;
    private Long candidatureId;
    private Long recruteurId;
}
