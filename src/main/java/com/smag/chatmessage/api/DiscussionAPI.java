package com.smag.chatmessage.api;

import com.smag.chatmessage.modele.Message;
import com.smag.chatmessage.services.DiscussionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiscussionAPI {
    @Autowired private DiscussionService discussionService;
    @GetMapping(value = "/users/{id}/discussions")
    private ResponseEntity getAllDiscussionsByIdUser(@PathVariable( "id") String id){
       return new ResponseEntity<>(discussionService.findDiscussionByIdUser(id),HttpStatus.OK);
    }

    @PostMapping(value ="/users/{transmetter}/contacts/{receiver}/addMessage")
    private ResponseEntity addMessage(@PathVariable("transmetter") String transmetter, @PathVariable("receiver") String receiver, @RequestBody Message message){
        if(message!=null) {
          if(discussionService.save(transmetter,receiver,message))
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value ="/users/{transmetter}/discussions/{receiver}")
    private ResponseEntity getDiscussionBetween(@PathVariable("transmetter") String transmetter,@PathVariable("receiver") String receiver){
        return new ResponseEntity<>(discussionService.getDiscussionBetween(transmetter,receiver),HttpStatus.OK);
    }

    @GetMapping(value ="/users/{transmetter}/discussions/{receiver}/messages")
    private ResponseEntity getMessagesBetween(@PathVariable("transmetter") String transmetter,@PathVariable("receiver") String receiver){
        return new ResponseEntity<>(discussionService.getMessagesBetween(transmetter,receiver),HttpStatus.OK);
    }

    @DeleteMapping(value ="/users/{transmetter}/discussions/{receiver}")
    private ResponseEntity deleteDiscussion(@PathVariable("transmetter") String transmetter, @PathVariable("receiver") String receiver){
        if(transmetter!=null && receiver!=null) {
            if(discussionService.delete(transmetter,receiver))
                return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value ="/users/{transmetter}/contacts/{receiver}/messages/{idmessage}")
    private ResponseEntity deleteMessage(@PathVariable("transmetter") String transmetter, @PathVariable("receiver") String receiver, @PathVariable("idmessage") String idmessage){
        if(transmetter!=null && receiver!=null && idmessage !=null) {
            if(discussionService.delete(transmetter,receiver,idmessage))
                return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
