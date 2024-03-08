package com.project.PFEBackEnd.controllers;

import com.project.PFEBackEnd.entities.Messages;
import com.project.PFEBackEnd.services.IMessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/messages")
public class MessagesController {
    private final IMessagesService messagesService;

    public MessagesController(IMessagesService messagesService) {
        this.messagesService = messagesService;
    }
    @PostMapping("/send/{senderId}/{receiverId}")
    public ResponseEntity<Void> sendMessage(@PathVariable Long senderId, @PathVariable Long receiverId, @RequestBody Messages message) {
        messagesService.envoiMessage(senderId, receiverId, message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/conversation/{senderId}/{receiverId}/{limit}")
    public ResponseEntity<List<Messages>> getConversation(@PathVariable Long senderId, @PathVariable Long receiverId, @PathVariable int limit) {
        List<Messages> conversation = messagesService.getConversationBySenderAndReceiver(senderId, receiverId, limit);
        return ResponseEntity.ok().body(conversation);
    }
    @PutMapping(path = "/update/{idMessage}")
    public void updateStatusMessage(@PathVariable Long idMessage){
        messagesService.updateStatusMessage(idMessage);
    }
}
