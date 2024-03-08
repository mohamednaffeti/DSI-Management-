package com.project.PFEBackEnd.controllers;

import com.project.PFEBackEnd.entities.Demande;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import com.project.PFEBackEnd.entities.enumerations.StatutDemande;
import com.project.PFEBackEnd.services.IDemandeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/request")
public class DemandeController {
    private final IDemandeService demandeService;

    public DemandeController(IDemandeService demandeService) {
        this.demandeService = demandeService;
    }
    @GetMapping("/getAll")
    public List<Demande> getAllDemandes() {
        return demandeService.getAll();
    }

    @PostMapping("/createRequest/{idUser}")
    public Demande createDemande(@PathVariable Long idUser, @RequestBody Demande demande) {
        return demandeService.createDemande(idUser, demande);
    }

    @PutMapping("/updateRequest")
    public Demande updateDemande(@RequestBody Demande demande) {
        return demandeService.updateDemande(demande);
    }

    @PutMapping("/updateStatus/{idDemande}/{newStatus}")
    public Demande updateStatusDemande(@PathVariable Long idDemande, @PathVariable StatutDemande newStatus) {
        return demandeService.updateStatusDemande(idDemande, newStatus);
    }

    @DeleteMapping("/delete/{idDemande}")
    public void deleteDemande(@PathVariable Long idDemande) {
        demandeService.deleteDemande(idDemande);
    }

    @GetMapping("/getAllByUser/{idUser}")
    public List<Demande> demandesByUser(@PathVariable Long idUser) {
        return demandeService.demandesByUser(idUser);
    }

    @GetMapping("/getAllByCategory/{category}")
    public List<Demande> demandesByCategory(@PathVariable DeviceType category) {
        return demandeService.demandesByCategory(category);
    }
}
