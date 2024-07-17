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
public class OfferRequest {
    //private Long id;
    private String titre;
    private String description;
    private String qualification;
    private String avantages;
    private String localisation;
    private String niveauExperience;
    private Integer nombrePostes;
    private String exigencesLangue;
    private String typeEmploi;
    private String image;
    private Date dateLimite;
    private String dateDePublication;
    private String etatOffer;
    private Long categorieId;
}
