package com.smag.chatmessage.services;

import com.smag.chatmessage.modele.Message;
import com.smag.chatmessage.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired private MessageRepository messageRepository;

    public List<Message> getMessagesByIdDiscussion(String idDiscussion){
        return messageRepository.findByIdDiscussion(idDiscussion);
    }

    public void save(Message message){
        messageRepository.save(message);
    }

    public Message exists(String transmetterId, String receiverId, String idMessage) {
        return messageRepository.findMessage(transmetterId,receiverId,idMessage);
    }
}
