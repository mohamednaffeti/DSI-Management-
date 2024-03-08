package com.project.PFEBackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Affectation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH-ss-mm")
    private LocalDateTime dateAffect = LocalDateTime.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-ss-mm")
    private LocalDateTime dateRetour ;

    @ManyToOne(fetch = FetchType.EAGER)
    private Device device;

    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur utilisateur;

    @Override
    public String toString() {
        return "Affectation{" +
                "id=" + id +
                ", dateAffect=" + dateAffect +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
