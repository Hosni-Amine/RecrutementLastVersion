package tn.tritux.pfe.recrutement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
    private String question;
    private List<String> options;
    private String reponseCorrecte;
    private double note;
    private Long testId;
}
