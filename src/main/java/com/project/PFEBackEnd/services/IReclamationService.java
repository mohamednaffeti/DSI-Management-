package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Demande;
import com.project.PFEBackEnd.entities.Reclamation;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import com.project.PFEBackEnd.entities.enumerations.StatusReclamation;
import com.project.PFEBackEnd.entities.enumerations.StatutDemande;

import java.util.List;

public interface IReclamationService {
    List<Reclamation> getAll();
    Reclamation createReclamation(Long idUser,Long idDevice,Reclamation reclamation);
    Reclamation updateReclamation(Reclamation reclamation);
    Reclamation updateStatusReclamation(Long idReclamation, StatusReclamation newStatus);
    void deleteReclamation(Long idReclamation);
    List<Reclamation> reclamationByUser(Long idUser);
    List<Reclamation> reclamationByCategory(DeviceType category);
    List<Reclamation> reclamationByDevice(Long idDevice);
}
