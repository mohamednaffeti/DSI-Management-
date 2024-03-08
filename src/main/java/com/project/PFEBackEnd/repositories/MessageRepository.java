package com.project.PFEBackEnd.repositories;

import com.project.PFEBackEnd.entities.Messages;
import com.project.PFEBackEnd.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Messages,Long> {
    @Query("SELECT m FROM Messages m WHERE (m.sender.id = :senderId AND m.receiver.id = :receiverId) OR (m.sender.id = :receiverId AND m.receiver.id = :senderId) ORDER BY m.dateEnvoie DESC")
    List<Messages> findMessagesBySenderAndReceiver(Long senderId, Long receiverId, Pageable pageable);

}

