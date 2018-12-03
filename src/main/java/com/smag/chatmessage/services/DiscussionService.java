package com.smag.chatmessage.services;

import com.smag.chatmessage.helper.GenerateCode;
import com.smag.chatmessage.modele.Discussion;
import com.smag.chatmessage.modele.Message;
import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.repositories.DiscussionRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiscussionService {
    @Autowired private DiscussionRepository discussionRepository;
    @Autowired private UserService userService;
    @Autowired private  MessageService messageService;

    public void save (Discussion discussion){
        discussionRepository.save(discussion);
    }

    public boolean save(String transmitterId, String receiverId, Message message){
        Discussion discussion = exists(transmitterId,receiverId);
        if(discussion != null && message!=null && !transmitterId.equals(receiverId)){
            message.setIdDiscussion(discussion.getIdDiscussion());
            System.out.println(message.toString());
            messageService.save(message);
            return true;
        }else if(message!=null && !transmitterId.equals(receiverId)) {
             try{
                    discussion = new Discussion();
                    discussion.setIdDiscussion(GenerateCode.clefUTC("DIS"));
                    message.setIdDiscussion(discussion.getIdDiscussion());

                    List<User> users = userService.findUsers(transmitterId,receiverId);
                    if(users.get(0).getIdUser().equals(transmitterId)){
                        discussion.setTransmetter(users.get(0).getIdUser());
                        discussion.setReceiver(users.get(1).getIdUser());
                    }else{
                        discussion.setTransmetter(users.get(1).getIdUser());
                        discussion.setReceiver(users.get(0).getIdUser());
                    }
                     save(discussion);
                  messageService.save(message);
                    return true;
                } catch (IndexOutOfBoundsException e){ e.getMessage();}
                 catch (ConstraintViolationException se){se.getMessage(); }
        }
        return false;
    }

    public Discussion exists(String transmitterId , String receiverId){
        Discussion discussion = discussionRepository.findByTransmetterAndReceiver(transmitterId,receiverId);
        if(discussion!=null)
            return discussion;
        return discussionRepository.findByTransmetterAndReceiver(receiverId,transmitterId);
    }

    public List<Discussion> findAllDiscussions(){
        return discussionRepository.findAll();
    }

    public List<Discussion> findDiscussionByIdUser(String id){
        return discussionRepository.findDiscussionByIdUser(id);
    }

    public List<Message> getMessagesBetween(String transmetter, String receiver) {
        try{
           String idDiscussion = exists(transmetter,receiver).getIdDiscussion();
           return messageService.getMessagesByIdDiscussion(idDiscussion);

        }catch (NullPointerException npe){
            npe.getMessage();
            return null;
        }
    }

    public Discussion getDiscussionBetween(String transmetter, String receiver) {
        return exists(transmetter,receiver);
    }

    public boolean delete(String transmetterId, String receiverId) {
        Discussion discussion = exists(transmetterId,receiverId);
        if(discussion!=null) {
            discussionRepository.delete(discussion);
            return true;
        }
        return false;
    }

    public boolean delete(String transmetterId, String receiverId, String idMessage) {
        Message message = messageService.exists(transmetterId,receiverId,idMessage);
        if(message.getDeleteStatus().intValue()==0){

        }else if(message.getDeleteStatus().intValue()==0){

        }else if(message.getDeleteStatus().intValue()==0){

        }
        return false;
    }
}
