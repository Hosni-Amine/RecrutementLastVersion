package tn.tritux.pfe.recrutement.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.tritux.pfe.recrutement.entity.Role;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String image_profile;
}
