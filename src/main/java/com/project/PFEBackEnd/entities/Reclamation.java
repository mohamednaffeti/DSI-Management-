package com.project.PFEBackEnd.entities;

import com.project.PFEBackEnd.entities.enumerations.StatusReclamation;
import com.project.PFEBackEnd.entities.enumerations.StatutDemande;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String titre;
    @Column(length = 1000)
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-ss-mm")
    private LocalDateTime dateReclamation = LocalDateTime.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-ss-mm")
    private LocalDateTime dateUpdate = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private StatusReclamation statut = StatusReclamation.OPEN;
    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur utilisateur;
    @ManyToOne(fetch = FetchType.EAGER)
    private Device device;
}
