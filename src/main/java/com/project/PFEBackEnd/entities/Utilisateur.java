package com.project.PFEBackEnd.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.PFEBackEnd.entities.enumerations.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;

    String password;
    @ManyToOne
    @JoinColumn(name = "manager")
    private Utilisateur manager;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-ss-mm")
    private LocalDateTime HiringDate = LocalDateTime.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-ss-mm")
    private LocalDateTime departureDate;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "utilisateur", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Affectation> affectations;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "utilisateur", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Demande> demandes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "utilisateur", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reclamation> reclamations;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sender", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Messages> sentMessages;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "receiver", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Messages> receivedMessages;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "utilisateur", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<HistoriqueUpdateUserDetails> historiques;
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
