package com.project.PFEBackEnd.entities.dto;

import com.project.PFEBackEnd.entities.Affectation;
import com.project.PFEBackEnd.entities.Messages;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.entities.enumerations.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Getter
@Setter
public class UserDTO {
    Long id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    Role role;
    private LocalDateTime departureDate;
    private Utilisateur manager;
    private List<Affectation> affectations;
    private List<Messages> sentMessages;
    private List<Messages> receivedMessages;


}
