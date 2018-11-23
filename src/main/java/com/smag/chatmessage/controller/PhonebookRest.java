package com.smag.chatmessage.controller;
import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.services.PhonebookService;
import com.smag.chatmessage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RepositoryRestController
@RestController
public class PhonebookRest {
    @Autowired private UserService userService;
    @Autowired private PhonebookService phonebookService;

    @GetMapping(value="/users/{id}/contacts")
    private ResponseEntity<?> getPhonebook(@PathVariable("id")  String id){
        List<User> contacts = phonebookService.getContacts(id);
        if (contacts !=null){
            return new ResponseEntity<>(contacts,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/users/{id}/contacts/phones")
    private ResponseEntity<?> getPhones(@PathVariable("id")  String id){
        List<String> phonebook = phonebookService.getOnlyPhonesNumber(id);
        if (phonebook !=null){
            return new ResponseEntity<>(phonebook,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/users/{id}/contacts/emails")
    private ResponseEntity<?> getEmails(@PathVariable("id")  String id){
        List<String> emails = phonebookService.getOnlyEmails(id);
        if (emails !=null){
            return new ResponseEntity<>(emails,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value="/users/{id}/contacts/surnames")
    private ResponseEntity<?> getSurnames(@PathVariable("id")  String id){
        List<String> phonebook = phonebookService.getOnlySurnames(id);
        if (phonebook !=null){
            return new ResponseEntity<>(phonebook,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/users/{id}/contacts/names")
    private ResponseEntity<?> getNames(@PathVariable("id")  String id){
        List<String> phonebook = phonebookService.getOnlyNames(id);
        if (phonebook !=null){
            return new ResponseEntity<>(phonebook,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
