package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Affectation;
import com.project.PFEBackEnd.entities.Device;
import com.project.PFEBackEnd.entities.Reclamation;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import com.project.PFEBackEnd.entities.enumerations.StatusReclamation;
import com.project.PFEBackEnd.exceptions.DataNotFoundException;
import com.project.PFEBackEnd.repositories.DeviceRepository;
import com.project.PFEBackEnd.repositories.ReclamationRepository;
import com.project.PFEBackEnd.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReclamationServiceImpl implements IReclamationService {
    private final ReclamationRepository reclamationRepository;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final IAffectationService affectationService;

    public ReclamationServiceImpl(ReclamationRepository reclamationRepository, UserRepository userRepository, DeviceRepository deviceRepository, IAffectationService affectationService) {
        this.reclamationRepository = reclamationRepository;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.affectationService = affectationService;
    }

    @Override
    public List<Reclamation> getAll() {
        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation createReclamation(Long idUser, Long idDevice, Reclamation reclamation) {
        Utilisateur utilisateur = userRepository.findById(idUser)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        Device device = deviceRepository.findById(idDevice)
                .orElseThrow(() -> new DataNotFoundException("Device not found"));

        reclamation.setUtilisateur(utilisateur);
        reclamation.setDevice(device);
        reclamation.setDateReclamation(LocalDateTime.now());
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation) {
        Reclamation existingReclamation = reclamationRepository.findById(reclamation.getId()).orElse(null);

        if (existingReclamation == null) {
            throw new DataNotFoundException("Reclamation not found");
        }

        existingReclamation.setTitre(reclamation.getTitre());
        existingReclamation.setDescription(reclamation.getDescription());
        existingReclamation.setDateUpdate(LocalDateTime.now());

        return reclamationRepository.save(existingReclamation);
    }

    @Override
    public Reclamation updateStatusReclamation(Long idReclamation, StatusReclamation newStatus) {
        Reclamation existingReclamation = reclamationRepository.findById(idReclamation).orElse(null);
        if (existingReclamation == null) {
            throw new DataNotFoundException("Reclamation not found");
        }

        existingReclamation.setStatut(newStatus);
        existingReclamation.setDateUpdate(LocalDateTime.now());

        return reclamationRepository.save(existingReclamation);
    }

    @Override
    public void deleteReclamation(Long idReclamation) {
            reclamationRepository.deleteById(idReclamation);
    }

    @Override
    public List<Reclamation> reclamationByUser(Long idUser) {
        return reclamationRepository.findReclamationsByUserId(idUser);
    }

    @Override
    public List<Reclamation> reclamationByCategory(DeviceType category) {
        return reclamationRepository.findReclamationsByCategory(category);
    }

    @Override
    public List<Reclamation> reclamationByDevice(Long idDevice) {
        return reclamationRepository.findReclamationsByDeviceId(idDevice);
    }
}
