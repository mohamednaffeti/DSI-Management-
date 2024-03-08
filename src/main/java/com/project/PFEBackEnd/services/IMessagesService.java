package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Messages;

import java.util.List;

public interface IMessagesService {
    void envoiMessage(Long idSender, Long idReceiver , Messages messages);

    List<Messages> getConversationBySenderAndReceiver(Long idSender,Long idReceiver,int Limit);
    void updateStatusMessage(Long idMessage);

}
