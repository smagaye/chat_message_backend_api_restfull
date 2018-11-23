package com.smag.chatmessage.services;

import com.smag.chatmessage.modele.Phonebook;
import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.repositories.PhonebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhonebookService {
    @Autowired private PhonebookRepository phonebookRepository;
    @Autowired private UserService userService;

    public void save(Phonebook phonebook){
        phonebookRepository.save(phonebook);
    }

    public Phonebook findById(String id){
        return  phonebookRepository.findByUserByProprietaire_IdUser(id);
    }

    public void delete(int id){
        phonebookRepository.delete(id);
    }

    public Phonebook getPhonebookByIdUser(String id){
        return phonebookRepository.findByUserByProprietaire_IdUser(id);
    }

    public List<String> getOnlyPhonesNumber(String id){
        List<User> contacts =getContacts(id);
        if (contacts !=null) {
            List<String> phones = new ArrayList<>();
            for (User user : contacts){
                phones.add(user.getPhone());
            }
            return phones;
        }
        return null;
    }

    public List<String> getOnlyEmails(String id){
        List<User> contacts =getContacts(id);
        if (contacts !=null) {
            List<String>  emails = new ArrayList<>();
            for (User user : contacts){
               emails.add(user.getEmail());
            }
            return emails;
        }
        return null;
    }

    public List<User> getContacts(String id) {
        Phonebook phonebook = getPhonebookByIdUser(id);
        if (phonebook !=null) {
            return userService.findAllContactById(phonebook.splitContactsId());
        }
        return null;
    }

    public List<String> getOnlySurnames(String id) {
        List<User> contacts = getContacts(id);
        if (contacts != null) {
            List<String> surnames = new ArrayList<>();
            for (User user : contacts) {
                if (user != null) surnames.add(user.getSurname());
            }
            return surnames;
        }
        return null;
    }

    public List<String> getOnlyNames(String id) {
        List<User> contacts = getContacts(id);
        if (contacts != null) {
            List<String> surnames = new ArrayList<>();
            for (User user : contacts) {
                if (user != null) surnames.add(user.getName());
            }
            return surnames;
        }
        return null;
    }
}
