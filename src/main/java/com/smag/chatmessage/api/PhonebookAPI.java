package com.smag.chatmessage.api;
import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.modele.UserRest;
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
public class PhonebookAPI {
    @Autowired private UserService userService;
    @Autowired private PhonebookService phonebookService;

    @PostMapping(value="/users/{id}/addContact")
    private ResponseEntity addUser(@PathVariable("id") String id, @RequestBody User user) {
        try{
            phonebookService.save(id,user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping(value="/users/{id}/updateContact")
    private ResponseEntity updateContact(@PathVariable("id") String id, @RequestBody User user) {
        try{
            phonebookService.save(id,user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping(value="/users/{id}/contacts")
    private ResponseEntity getPhonebook(@PathVariable("id")  String id){
        List<UserRest> contacts = phonebookService.getContacts(id);
        if (contacts !=null){
            return new ResponseEntity<>(contacts,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/users/{id}/contacts/phones")
    private ResponseEntity getPhones(@PathVariable("id")  String id){
        List<String> phonebook = phonebookService.getOnlyPhonesNumber(id);
        if (phonebook !=null){
            return new ResponseEntity<>(phonebook,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/users/{id}/contacts/emails")
    private ResponseEntity getEmails(@PathVariable("id")  String id){
        List<String> emails = phonebookService.getOnlyEmails(id);
        if (emails !=null){
            return new ResponseEntity<>(emails,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/users/{id}/contacts/surnames")
    private ResponseEntity getSurnames(@PathVariable("id")  String id){
        List<String> phonebook = phonebookService.getOnlySurnames(id);
        if (phonebook !=null){
            return new ResponseEntity<>(phonebook,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/users/{id}/contacts/names")
    private ResponseEntity getNames(@PathVariable("id")  String id){
        List<String> phonebook = phonebookService.getOnlyNames(id);
        if (phonebook !=null){
            return new ResponseEntity<>(phonebook,HttpStatus.OK);
        }return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value="/users/{idUser}/contacts/{idcontact}")
    private ResponseEntity deleteContact(@PathVariable("idUser") String idUser,@PathVariable("idcontact") String idcontact){
        return (phonebookService.deleteContactFromPhonebook(idUser,idcontact)) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
