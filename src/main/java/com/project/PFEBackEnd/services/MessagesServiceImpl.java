package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Messages;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.exceptions.DataNotFoundException;
import com.project.PFEBackEnd.repositories.MessageRepository;
import com.project.PFEBackEnd.repositories.UserRepository;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessagesServiceImpl implements IMessagesService {
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public MessagesServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void envoiMessage(Long idSender, Long idReceiver, Messages messages) {
        Utilisateur userSender = userRepository.findById(idSender).orElse(null);
        Utilisateur userReceiver = userRepository.findById(idReceiver).orElse(null);
        messages.setSender(userSender);
        messages.setReceiver(userReceiver);
        messages.setDateEnvoie(LocalDateTime.now());
        messageRepository.save(messages);
    }

    @Override
    public List<Messages> getConversationBySenderAndReceiver(Long idSender, Long idReceiver, int limit) {
        Utilisateur sender = userRepository.findById(idSender).orElse(null);
        Utilisateur receiver = userRepository.findById(idReceiver).orElse(null);
        if (sender == null || receiver == null) {
            throw new DataNotFoundException("Users not found ");
        }
        Pageable pageable = PageRequest.of(0, limit);
        return messageRepository.findMessagesBySenderAndReceiver(idSender, idReceiver, pageable);
    }

    @Override
    public void updateStatusMessage(Long idMessage) {
        Messages messages = messageRepository.findById(idMessage).orElse(null);
        if(messages == null){
            throw new DataNotFoundException("Message not exist");
        }else{
            messages.setLu(true);
            messageRepository.save(messages);
        }
    }

}
