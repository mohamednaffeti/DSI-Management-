package com.project.PFEBackEnd.controllers;

import com.project.PFEBackEnd.entities.Affectation;
import com.project.PFEBackEnd.services.IAffectationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/affectation")
public class AffectationController {
    private final IAffectationService affectationService;

    public AffectationController(IAffectationService affectationService) {
        this.affectationService = affectationService;
    }
    @GetMapping("/getAffectation/{idUser}/{idDevice}")
    public ResponseEntity<Affectation> getAffectation(@PathVariable Long idUser, @PathVariable Long idDevice) {
        Affectation affectation = affectationService.getAffectation(idUser, idDevice);
        return ResponseEntity.ok().body(affectation);
    }

    @PostMapping("/add/{idUser}/{idDevice}")
    public ResponseEntity<Affectation> addAffectation(@PathVariable Long idUser, @PathVariable Long idDevice) {
        Affectation createdAffectation = affectationService.addAffectation(idUser, idDevice);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAffectation);
    }
    @GetMapping(path = "/addV1/{username}/{serialNumber}")
    public ResponseEntity<Affectation> addAffectationV1(@PathVariable String username, @PathVariable String serialNumber) {
        Affectation createdAffectation = affectationService.addAffectationWithUsername(username, serialNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAffectation);
    }

    @PutMapping("/update/{idAffectation}/{idDevice}")
    public ResponseEntity<Affectation> updateAffectation(@PathVariable Long idAffectation, @PathVariable Long idDevice) {
        Affectation updatedAffectation = affectationService.updateAffectation(idAffectation, idDevice);
        return ResponseEntity.ok().body(updatedAffectation);
    }

    @PutMapping("/desaffectation/{id}")
    public ResponseEntity<Affectation> deffectation(@PathVariable Long id) {
        Affectation affectation =affectationService.desaffectation(id);
        return ResponseEntity.ok().body(affectation);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Affectation>> getAllAffectations() {
        List<Affectation> affectations = affectationService.getAll();
        return ResponseEntity.ok().body(affectations);
    }

    @GetMapping("/getByUser/{idUser}")
    public ResponseEntity<List<Affectation>> getAllByUser(@PathVariable Long idUser) {
        List<Affectation> affectations = affectationService.getAllByUser(idUser);
        return ResponseEntity.ok().body(affectations);
    }

    @GetMapping("/getByDateAffectation/{dateAffectation}")
    public ResponseEntity<List<Affectation>> getAllByDateAffectation(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateAffectation) {


        List<Affectation> affectations = affectationService.getAllByDateAffectation(dateAffectation);
        return ResponseEntity.ok().body(affectations);
    }
}
