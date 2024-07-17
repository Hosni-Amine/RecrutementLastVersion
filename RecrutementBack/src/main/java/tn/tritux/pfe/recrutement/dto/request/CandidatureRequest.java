package tn.tritux.pfe.recrutement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureRequest {

    private String etat;
    private Long profileId;
    private Long idOffer;
    private Date dateCandidature;


}
