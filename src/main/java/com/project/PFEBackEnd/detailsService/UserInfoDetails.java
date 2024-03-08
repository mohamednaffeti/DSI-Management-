package com.project.PFEBackEnd.detailsService;

import com.project.PFEBackEnd.entities.Affectation;
import com.project.PFEBackEnd.entities.Messages;
import com.project.PFEBackEnd.entities.enumerations.Role;
import com.project.PFEBackEnd.entities.Utilisateur;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Getter
public class UserInfoDetails implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    private Role role;
    List<Affectation> affectations;
    private LocalDateTime departureDate;
    private List<Messages> sentMessages;
    private List<Messages> receivedMessages;
    List<GrantedAuthority> authorities;
    public UserInfoDetails(Utilisateur user){
        id=user.getId();
        userName= user.getUserName();
        password= user.getPassword();
        firstName= user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        manager = user.getManager();
        role=user.getRole();
        HiringDate = user.getHiringDate();
        departureDate = user.getDepartureDate();
        affectations = user.getAffectations();
        sentMessages = user.getSentMessages();
        receivedMessages = user.getReceivedMessages();
        authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
