package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Demande;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import com.project.PFEBackEnd.entities.enumerations.StatutDemande;

import java.util.List;

public interface IDemandeService {

        List<Demande> getAll();
        Demande createDemande(Long idUser,Demande demande);
        Demande updateDemande(Demande demande);
        Demande updateStatusDemande(Long idDemande, StatutDemande newStatus);
        void deleteDemande(Long idDemande);
        List<Demande> demandesByUser(Long idUser);
        List<Demande> demandesByCategory(DeviceType category);

}
