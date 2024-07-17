package tn.tritux.pfe.recrutement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategorieResponse {
    private Long id;
    private String nom;
    private String image;
    private List<OfferResponse> offers;
}
