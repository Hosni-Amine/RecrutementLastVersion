package tn.tritux.pfe.recrutement.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.tritux.pfe.recrutement.entity.Categorie;
import tn.tritux.pfe.recrutement.entity.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    @EntityGraph(attributePaths = {"categorie"})
    List<Offer> findAllByCategorie(Categorie categorie);
}
