package com.project.PFEBackEnd.repositories;

import com.project.PFEBackEnd.entities.Reclamation;
import com.project.PFEBackEnd.entities.enumerations.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
    @Query("SELECT r FROM Reclamation r WHERE r.utilisateur.id = :userId")
    List<Reclamation> findReclamationsByUserId(@Param("userId") Long userId);
    @Query("SELECT r FROM Reclamation r WHERE r.device.category = :category")
    List<Reclamation> findReclamationsByCategory(@Param("category") DeviceType category);
    @Query("SELECT r FROM Reclamation r WHERE r.device.id = :deviceId")
    List<Reclamation> findReclamationsByDeviceId(@Param("deviceId") Long deviceId);
}
