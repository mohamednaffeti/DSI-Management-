package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Affectation;
import com.project.PFEBackEnd.entities.Device;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.entities.enumerations.StatusMaterial;
import com.project.PFEBackEnd.exceptions.DataNotFoundException;
import com.project.PFEBackEnd.exceptions.DeviceNotAvailableException;
import com.project.PFEBackEnd.repositories.AffectationRepository;
import com.project.PFEBackEnd.repositories.DeviceRepository;
import com.project.PFEBackEnd.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AffectationServiceImpl implements IAffectationService {
    private final AffectationRepository affectationRepository;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final IDeviceService deviceService;

    public AffectationServiceImpl(AffectationRepository affectationRepository, UserRepository userRepository, DeviceRepository deviceRepository, IDeviceService deviceService) {
        this.affectationRepository = affectationRepository;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.deviceService = deviceService;
    }

    @Override
    public Affectation getAffectation(Long idUser, Long idDevice) {
        return affectationRepository.findByUtilisateurIdAndDevice_Id(idUser,idDevice);
    }

    @Override
    public List<Affectation> getAllByUser(Long idUser) {
        return affectationRepository.findByUtilisateurId(idUser);

    }

    @Override
    public List<Affectation> getAllByDateAffectation(LocalDate dateAffectation) {
        List<Affectation> affectations = affectationRepository.findAll();
        List<Affectation> affectationsFiltred = affectations.stream()
                .filter(affectation->affectation.getDateAffect().toLocalDate().equals(dateAffectation))
                .toList();
        return affectationsFiltred;
    }

    @Override
    public Affectation addAffectation(Long idUser, Long idDevice) {
        Affectation affectation = new Affectation();
        affectation.setUtilisateur(userRepository.findById(idUser).orElse(null));
        affectation.setDevice(deviceRepository.findById(idDevice).orElse(null));
        Affectation affectation1;
        if(affectation.getUtilisateur()==null || affectation.getDevice()==null){
            throw new DataNotFoundException("user or device not found");
        }else if (!affectation.getDevice().getStatut().equals(StatusMaterial.Disponible)) {
            throw new DataNotFoundException(" device is not available now");
        }else{

           affectation1= affectationRepository.save(affectation);
           Device device = deviceRepository.findById(idDevice).orElse(null);

            device.setStatut(StatusMaterial.OCCUPER);
            deviceService.updateDevice(device , device.getId());
        }

        return affectation1;
    }

    @Override
    public Affectation updateAffectation(Long idAffectation, Long idDevice) {

        Affectation affectationToUpdate = affectationRepository.findById(idAffectation).orElse(null);

        if (affectationToUpdate == null) {
            throw new DataNotFoundException("Affectation not found");
        }
        Device device1 = affectationToUpdate.getDevice();
        device1.setStatut(StatusMaterial.Disponible);
        deviceService.updateDevice(device1 , device1.getId());
        Device device = deviceRepository.findById(idDevice).orElse(null);
        if (device == null) {
            throw new DataNotFoundException("Device not found");
        }
        device.setStatut(StatusMaterial.OCCUPER);
        deviceService.updateDevice(device , device.getId());
        affectationToUpdate.setDevice(device);
        affectationToUpdate.setDateAffect(LocalDateTime.now());

        return affectationRepository.save(affectationToUpdate);
    }
    @Override
    public Affectation desaffectation(Long idAffectation) {
        Affectation affectationToUpdate = affectationRepository.findById(idAffectation).orElse(null);
        Device device = affectationToUpdate.getDevice();
        device.setStatut(StatusMaterial.Disponible);

        if (affectationToUpdate == null) {
            throw new DataNotFoundException("Affectation not found");
        }

        affectationToUpdate.setDateRetour(LocalDateTime.now());
        deviceService.updateDevice(device , device.getId());
        return affectationRepository.save(affectationToUpdate);
    }

    @Override
    public Affectation addAffectationWithUsername(String username, String serailNumber) {
        Utilisateur user = userRepository.findByUserName(username).orElse(null);
        Device device = deviceRepository.getDeviceBySerialNumber(serailNumber);

        if(user == null || device == null){
            throw new DataNotFoundException("user or device not found");
        }else if(!device.getStatut().equals(StatusMaterial.Disponible)) {
            throw new DeviceNotAvailableException("Device already occupied");
        }else
            {
                device.setStatut(StatusMaterial.OCCUPER);
                deviceService.updateDevice(device , device.getId());

                Affectation affectation = new Affectation();
                affectation.setUtilisateur(user);
                affectation.setDevice(device);
                affectation.setDateAffect(LocalDateTime.now());;
                return affectationRepository.save(affectation);
            }
        }




    @Override
    public List<Affectation> getAll() {

        return affectationRepository.findAll();
    }
}
