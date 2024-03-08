package com.project.PFEBackEnd.repositories;

import com.project.PFEBackEnd.entities.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Repository
public interface AffectationRepository extends JpaRepository<Affectation,Long> {
    Affectation findByUtilisateurIdAndDevice_Id(Long utilisateur_id, long device_id);
    List<Affectation> findByUtilisateurId(Long idUtilisateur);

}
