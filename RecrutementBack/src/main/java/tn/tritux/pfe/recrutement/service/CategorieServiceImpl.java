package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.dto.request.CategorieRequest;
import tn.tritux.pfe.recrutement.dto.response.CategorieResponse;
import tn.tritux.pfe.recrutement.entity.Categorie;
import tn.tritux.pfe.recrutement.repository.CategorieRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService{
    private final CategorieRepository categorieRepository;

    @Override
    public CategorieResponse ajouterCategorie(CategorieRequest categorieRequest) {
        Categorie categorie = requestToEntity(categorieRequest);
        categorie = categorieRepository.save(categorie);
        return entityToResponse(categorie);
    }

    @Override
    public CategorieResponse modifierCategorie(CategorieRequest categorieRequest) {
        Categorie categorie = requestToEntity(categorieRequest);
        categorie = categorieRepository.save(categorie);
        return entityToResponse(categorie);
    }

    @Override
    public void supprimerCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public CategorieResponse afficherCategorieParId(Long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie not found"));
        return entityToResponse(categorie);
    }

    @Override
    public List<CategorieResponse> afficherCategorie() {
        List<Categorie> categories = categorieRepository.findAll();
        return categories.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    private Categorie requestToEntity(CategorieRequest categorieRequest) {
        Categorie categorie = new Categorie();
        categorie.setNom(categorieRequest.getNom());
        categorie.setImage(categorieRequest.getImage());
        return categorie;
    }

    private CategorieResponse entityToResponse(Categorie categorie) {
        CategorieResponse categorieResponse = new CategorieResponse();
        categorieResponse.setId(categorie.getId());
        categorieResponse.setNom(categorie.getNom());
        categorieResponse.setImage(categorie.getImage());
        return categorieResponse;
    }
}
