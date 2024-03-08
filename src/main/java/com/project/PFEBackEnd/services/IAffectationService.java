package com.project.PFEBackEnd.services;



import com.project.PFEBackEnd.entities.Affectation;

import java.time.LocalDate;
import java.util.List;

public interface IAffectationService {
    Affectation getAffectation(Long idUser , Long idDevice);
    List<Affectation> getAllByUser(Long idUser);
    List<Affectation> getAllByDateAffectation(LocalDate dateAffectation);
    Affectation addAffectation (Long idUser , Long idDevice);
    Affectation updateAffectation(Long idAffectaion , Long idDevice);
   // Affectation deleteAffectation(Long id);
    List<Affectation> getAll();
    Affectation desaffectation(Long idAffectation);
    Affectation addAffectationWithUsername(String username,String serailNumber);
}
