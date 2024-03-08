package com.project.PFEBackEnd.controllers;

import com.project.PFEBackEnd.entities.Reclamation;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import com.project.PFEBackEnd.entities.enumerations.StatusReclamation;
import com.project.PFEBackEnd.services.IReclamationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/reclamation")
public class ReclamationController {

    private final IReclamationService reclamationService;

    public ReclamationController(IReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }
    @GetMapping("/getAll")
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAll();
    }

    @PostMapping("/addReclamation/{idUser}/{idDevice}")
    public Reclamation createReclamation(@PathVariable Long idUser, @PathVariable Long idDevice, @RequestBody Reclamation reclamation) {
        return reclamationService.createReclamation(idUser, idDevice, reclamation);
    }

    @PutMapping("/updateReclamation")
    public Reclamation updateReclamation(@RequestBody Reclamation reclamation) {
        return reclamationService.updateReclamation(reclamation);
    }

    @PutMapping("/updateStatus/{idReclamation}")
    public Reclamation updateStatusReclamation(@PathVariable Long idReclamation, @RequestParam StatusReclamation newStatus) {
        return reclamationService.updateStatusReclamation(idReclamation, newStatus);
    }

    @DeleteMapping("/delete/{idReclamation}")
    public void deleteReclamation(@PathVariable Long idReclamation) {
        reclamationService.deleteReclamation(idReclamation);
    }

    @GetMapping("/getByUser/{idUser}")
    public List<Reclamation> getReclamationsByUser(@PathVariable Long idUser) {
        return reclamationService.reclamationByUser(idUser);
    }

    @GetMapping("/getByCategory/{category}")
    public List<Reclamation> getReclamationsByCategory(@PathVariable DeviceType category) {
        return reclamationService.reclamationByCategory(category);
    }

    @GetMapping("/getByDevice/{idDevice}")
    public List<Reclamation> getReclamationsByDevice(@PathVariable Long idDevice) {
        return reclamationService.reclamationByDevice(idDevice);
    }
}
