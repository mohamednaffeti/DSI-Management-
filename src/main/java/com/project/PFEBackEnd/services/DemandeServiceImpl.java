package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Demande;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import com.project.PFEBackEnd.entities.enumerations.StatutDemande;
import com.project.PFEBackEnd.exceptions.DataNotFoundException;
import com.project.PFEBackEnd.repositories.DemandeRepository;
import com.project.PFEBackEnd.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DemandeServiceImpl implements IDemandeService {
    private final DemandeRepository demandeRepository;
    private final UserRepository userRepository;

    public DemandeServiceImpl(DemandeRepository demandeRepository, UserRepository userRepository) {
        this.demandeRepository = demandeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Demande> getAll() {
        return demandeRepository.findAll();
    }

    @Override
    public Demande createDemande(Long idUser, Demande demande) {
        Utilisateur utilisateur = userRepository.findById(idUser)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        demande.setUtilisateur(utilisateur);
        return demandeRepository.save(demande);
    }


    @Override
    public Demande updateDemande(Demande demande) {
        Demande existingDemande = demandeRepository.findById(demande.getId()).orElse(null);


        if (existingDemande == null) {
            throw new DataNotFoundException("Request Not found");
        } else {

            existingDemande.setTitre(demande.getTitre());
            existingDemande.setDescription(demande.getDescription());
            existingDemande.setDateDemande(existingDemande.getDateDemande());
            existingDemande.setDateUpdate(LocalDateTime.now());
            existingDemande.setStatut(demande.getStatut());
            existingDemande.setDeviceType(demande.getDeviceType());
            existingDemande.setUtilisateur(demande.getUtilisateur());

            return demandeRepository.save(existingDemande);
        }
    }


    @Override
    public Demande updateStatusDemande(Long idDemande, StatutDemande newStatus) {
        Demande existingDemande = demandeRepository.findById(idDemande).orElse(null);
        if (existingDemande == null) {
            throw new DataNotFoundException("Request Not found");
        } else {

            existingDemande.setStatut(newStatus);
            existingDemande.setDateUpdate(LocalDateTime.now());

            return demandeRepository.save(existingDemande);
        }
    }

    @Override
    public void deleteDemande(Long idDemande) {
        demandeRepository.deleteById(idDemande);
    }

    @Override
    public List<Demande> demandesByUser(Long idUser) {
        return demandeRepository.findByUserId(idUser);
    }

    @Override
    public List<Demande> demandesByCategory(DeviceType category) {
        return demandeRepository.findByCategory(category);
    }
}
