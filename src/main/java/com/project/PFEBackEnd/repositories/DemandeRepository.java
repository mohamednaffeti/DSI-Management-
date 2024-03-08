package com.project.PFEBackEnd.repositories;

import com.project.PFEBackEnd.entities.Demande;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande,Long> {
    @Query("SELECT d FROM Demande d WHERE d.utilisateur.id = :userId")
    List<Demande> findByUserId(Long userId);

    @Query("SELECT d FROM Demande d WHERE d.deviceType = :category")
    List<Demande> findByCategory(DeviceType category);
}
