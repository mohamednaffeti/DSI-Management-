package com.project.PFEBackEnd.repositories;

import com.project.PFEBackEnd.entities.Affectation;
import com.project.PFEBackEnd.entities.HistoriqueUpdateUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoriqueUpdateUserDetails,Long> {
    List<HistoriqueUpdateUserDetails> findByUtilisateurIdAndAndUpdateDate(Long idUser, LocalDate date);
    List<HistoriqueUpdateUserDetails> findByUtilisateurId(Long idUtilisateur);
}
