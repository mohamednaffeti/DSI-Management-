package com.project.PFEBackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import com.project.PFEBackEnd.entities.enumerations.StatusMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Device implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(unique = true)
    private String serialNumber;

    private String deviceName;
    @Enumerated(EnumType.STRING)
    private DeviceType category;

    @Enumerated(EnumType.STRING)
    private StatusMaterial statut= StatusMaterial.Disponible;


    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Affectation> affectations;
    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reclamation> reclamations;

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", category=" + category +
                ", statut=" + statut +
                '}';
    }
}
