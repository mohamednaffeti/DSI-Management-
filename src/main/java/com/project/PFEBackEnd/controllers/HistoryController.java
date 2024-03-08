package com.project.PFEBackEnd.controllers;

import com.project.PFEBackEnd.entities.HistoriqueUpdateUserDetails;
import com.project.PFEBackEnd.services.IHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/history")
public class HistoryController {
    private final IHistoryService historyService;

    public HistoryController(IHistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<HistoriqueUpdateUserDetails>> getHistoryByUser(@PathVariable Long userId) {
        List<HistoriqueUpdateUserDetails> history = historyService.getAllByUser(userId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{userId}/{date}")
    public ResponseEntity<List<HistoriqueUpdateUserDetails>> getHistoryByUserAndDate(@PathVariable Long userId,
                                                                                     @PathVariable LocalDate date) {
        List<HistoriqueUpdateUserDetails> history = historyService.getHistoryByUserAndDate(userId, date);
        return ResponseEntity.ok(history);
    }

   @GetMapping("/getAll")
   public ResponseEntity<List<HistoriqueUpdateUserDetails>> getAll(){
       List<HistoriqueUpdateUserDetails> history = historyService.getAll();
       return ResponseEntity.ok(history);
   }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        historyService.deleteHistory(id);
        return ResponseEntity.noContent().build();
    }
}
