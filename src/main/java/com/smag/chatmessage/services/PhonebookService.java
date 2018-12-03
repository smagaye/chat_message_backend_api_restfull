package com.smag.chatmessage.services;

import com.smag.chatmessage.helper.GenerateCode;
import com.smag.chatmessage.modele.Phonebook;
import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.modele.UserRest;
import com.smag.chatmessage.repositories.PhonebookRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhonebookService {
    @Autowired private PhonebookRepository phonebookRepository;
    @Autowired private UserService userService;
    @Autowired private PhonebookService phonebookService;

    public void save(Phonebook phonebook){
        phonebookRepository.save(phonebook);
    }

    public void save(String idProprietaire, User contact){
        User proprietaire = userService.findByIdUser(idProprietaire);
        User userFound =null;
        if(proprietaire!=null) {
            userFound = userService.findByEmailOrPhone(contact.getEmail(), contact.getPhone());
            if(userFound==null) {
                userService.save(contact);
            }
                Phonebook newPhonebook  = phonebookService.getPhonebookByIdUser(idProprietaire);
                try{
                    if(!newPhonebook.getListPhonebook().contains(contact.getIdUser()))
                     newPhonebook.setListPhonebook(newPhonebook.getListPhonebook() + "__"+ contact.getIdUser());
                }catch (NullPointerException ex){
                    newPhonebook = new Phonebook();
                    newPhonebook.setIdPhonebook(new Long(GenerateCode.getRandomInteger()));
                    newPhonebook.setOwner(proprietaire.getIdUser());
                    newPhonebook.setListPhonebook(contact.getIdUser());
                }
                save(newPhonebook);
        }
    }

    public Phonebook findById(String id){
        return  phonebookRepository.findByOwner(id);
    }

    public void delete(int id){
        phonebookRepository.delete(id);
    }

    public Phonebook getPhonebookByIdUser(String id){
        return phonebookRepository.findByOwner(id);
    }

    public List<String> getOnlyPhonesNumber(String id){
        List<UserRest> contacts =getContacts(id);
        if (contacts !=null) {
            List<String> phones = new ArrayList<>();
            for (UserRest user : contacts){
                phones.add(user.getPhone());
            }
            return phones;
        }
        return null;
    }

    public List<String> getOnlyEmails(String id){
        List<UserRest> contacts =getContacts(id);
        if (contacts !=null) {
            List<String>  emails = new ArrayList<>();
            for (UserRest user : contacts){
               emails.add(user.getEmail());
            }
            return emails;
        }
        return null;
    }

    public List<UserRest> getContacts(String id) {
        Phonebook phonebook = getPhonebookByIdUser(id);
        if (phonebook !=null) {
            return userService.findAllContactById(phonebook.splitContactsId());
        }
        return null;
    }

    public List<String> getOnlySurnames(String id) {
        List<UserRest> contacts = getContacts(id);
        if (contacts != null) {
            List<String> surnames = new ArrayList<>();
            for (UserRest user : contacts) {
                if (user != null) surnames.add(user.getSurname());
            }
            return surnames;
        }
        return null;
    }

    public List<String> getOnlyNames(String id) {
        List<UserRest> contacts = getContacts(id);
        if (contacts != null) {
            List<String> surnames = new ArrayList<>();
            for (UserRest user : contacts) {
                if (user != null) surnames.add(user.getName());
            }
            return surnames;
        }
        return null;
    }

    public boolean deleteContactFromPhonebook(String idUser, String value){
        Phonebook phonebook = findById(idUser);
        if(phonebook!=null)
        try {
            List<String> contacts = phonebook.splitContactsId();
            String idContact =userService.findByEmailOrPhone(value,value).getIdUser();
            System.out.println(contacts);
            contacts.remove(idContact);
            System.out.println(contacts);
            String listContacts="";
            for(int i = 0;i<contacts.size()-1;i++){
                listContacts+=contacts.get(i);
                listContacts+="__";
            }
            System.out.println(listContacts);
            listContacts+=(contacts.get(contacts.size()-1));
            if(!listContacts.equals(""))
                phonebook.setListPhonebook(listContacts);
            save(phonebook);
            return true;
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
