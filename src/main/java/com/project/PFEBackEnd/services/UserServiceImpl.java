package com.project.PFEBackEnd.services;


import com.project.PFEBackEnd.detailsService.UserInfoDetails;
import com.project.PFEBackEnd.entities.HistoriqueUpdateUserDetails;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.exceptions.DataNotFoundException;
import com.project.PFEBackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IHistoryService historyService;



    @Override
    public List<Utilisateur> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Utilisateur createManager(Utilisateur user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Utilisateur createUser(Long idManager, Utilisateur user) {
        Utilisateur manager = userRepository.findById(idManager).orElse(null);
        if(manager == null){
            throw new DataNotFoundException("Manager not found");
        }else{
            user.setManager(manager);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

    }

    @Override
    public Utilisateur updateUser(Utilisateur user) {

        Utilisateur utilisateur = findById(user.getId());


        if (utilisateur != null) {
            HistoriqueUpdateUserDetails history = new HistoriqueUpdateUserDetails();
            history.setUserName(utilisateur.getUserName());
            history.setFirstName(utilisateur.getFirstName());
            history.setLastName(utilisateur.getLastName());
            history.setEmail(utilisateur.getEmail());
            history.setPhoneNumber(utilisateur.getPhoneNumber());
            history.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            history.setManager(utilisateur.getManager());
            history.setHiringDate(utilisateur.getHiringDate());
            history.setRole(utilisateur.getRole());
            //



            utilisateur.setUserName(user.getUserName());
            utilisateur.setFirstName(user.getFirstName());
            utilisateur.setLastName(user.getLastName());
            utilisateur.setEmail(user.getEmail());
            utilisateur.setPhoneNumber(user.getPhoneNumber());
            utilisateur.setPassword(passwordEncoder.encode(user.getPassword()));
            utilisateur.setManager(user.getManager());
            utilisateur.setHiringDate(user.getHiringDate());
            utilisateur.setRole(user.getRole());



            userRepository.save(utilisateur);
            historyService.addHistory(user.getId(), history);
            return utilisateur;

        } else {

            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public Utilisateur departUser(Long idUser) {
        Utilisateur existingUser = userRepository.findById(idUser).orElse(null);
        if (existingUser == null) {
            throw new DataNotFoundException("User not found");
        }
        existingUser.setDepartureDate(LocalDateTime.now());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Utilisateur findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws DataNotFoundException {
        Optional<Utilisateur> userInfo = userRepository.findByUserName(username);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new DataNotFoundException("User not found: " + username));
    }

    @Override
    public List<Utilisateur> findmanagers() {
        return userRepository.findManagers();
    }


}
