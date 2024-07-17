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
public class SignUPRequest {

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String image_profile;

}
