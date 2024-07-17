package tn.tritux.pfe.recrutement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.tritux.pfe.recrutement.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
