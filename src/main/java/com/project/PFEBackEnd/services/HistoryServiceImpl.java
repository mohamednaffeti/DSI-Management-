package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.HistoriqueUpdateUserDetails;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.exceptions.DataNotFoundException;
import com.project.PFEBackEnd.repositories.HistoryRepository;
import com.project.PFEBackEnd.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class HistoryServiceImpl implements IHistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository, UserRepository userRepository) {
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<HistoriqueUpdateUserDetails> getHistoryByUserAndDate(Long idUser, LocalDate date) {
        List<HistoriqueUpdateUserDetails> listeByUser = this.getAllByUser(idUser);
        List<HistoriqueUpdateUserDetails> listeFiltred = listeByUser.stream()
                .filter(history -> history.getUpdateDate().toLocalDate().equals(date))
                .toList();
        return listeFiltred;
    }

    @Override
    public List<HistoriqueUpdateUserDetails> getAllByUser(Long idUser) {
        return historyRepository.findByUtilisateurId(idUser);
    }

    @Override
    public HistoriqueUpdateUserDetails addHistory(Long idUser, HistoriqueUpdateUserDetails history) {
        Utilisateur utilisateur = userRepository.findById(idUser)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        history.setUtilisateur(utilisateur);
        return historyRepository.save(history);
    }

    @Override
    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }

    @Override
    public List<HistoriqueUpdateUserDetails> getAll() {
        return historyRepository.findAll();
    }
}
