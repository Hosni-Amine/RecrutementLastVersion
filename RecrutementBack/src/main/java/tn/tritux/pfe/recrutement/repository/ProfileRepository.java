package tn.tritux.pfe.recrutement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.tritux.pfe.recrutement.entity.Profile;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {

    Optional<Profile> findByUserId(Long aLong);
}
