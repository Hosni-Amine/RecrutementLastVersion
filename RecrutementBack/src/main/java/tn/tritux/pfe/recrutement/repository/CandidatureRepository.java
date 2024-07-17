package tn.tritux.pfe.recrutement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.tritux.pfe.recrutement.entity.Candidature;
import tn.tritux.pfe.recrutement.entity.Offer;

import java.util.List;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature,Long> {

    List<Candidature> findAllByOfferId(Long id);
}
