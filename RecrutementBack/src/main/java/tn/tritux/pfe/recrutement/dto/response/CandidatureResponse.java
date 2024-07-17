package tn.tritux.pfe.recrutement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureResponse {

    private Long id;
    private Long profileId;
    private Long offerId;
    private String etat;
    private Date dateCandidature;
    private List<TestResultResponse> testResultResponseList;

}
