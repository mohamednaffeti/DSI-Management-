package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    List<Utilisateur> getAllUsers();
    Utilisateur createManager(Utilisateur user);
    Utilisateur createUser(Long idManager,Utilisateur user);
    Utilisateur updateUser(Utilisateur user);
    Utilisateur departUser(Long idUser);
    void deleteUser(Long id);
    Utilisateur findById(Long id);
    UserDetails loadUserByUsername(String username);
    List<Utilisateur> findmanagers();
}
