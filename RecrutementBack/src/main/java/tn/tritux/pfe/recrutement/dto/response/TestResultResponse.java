package tn.tritux.pfe.recrutement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestResultResponse {
    private Long id;
    private double noteTotal;
    private LocalDateTime date;
    private Long testId;
    private Long candidatureId;
}
