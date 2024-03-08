package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Affectation;
import com.project.PFEBackEnd.entities.HistoriqueUpdateUserDetails;

import java.time.LocalDate;
import java.util.List;

public interface IHistoryService {
    List<HistoriqueUpdateUserDetails> getHistoryByUserAndDate(Long idUser , LocalDate date);
    List<HistoriqueUpdateUserDetails> getAllByUser(Long idUser);

    HistoriqueUpdateUserDetails addHistory (Long idUser , HistoriqueUpdateUserDetails history);

    void deleteHistory(Long id);
    List<HistoriqueUpdateUserDetails> getAll();
}
