package com.project.PFEBackEnd.repositories;

import com.project.PFEBackEnd.entities.Utilisateur;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByUserName(String username);
    @Query("SELECT u FROM Utilisateur u WHERE u.role = 'MANAGER'")
    List<Utilisateur> findManagers();
}
