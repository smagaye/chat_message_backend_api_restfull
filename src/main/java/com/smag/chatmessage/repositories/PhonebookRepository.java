package com.smag.chatmessage.repositories;

import com.smag.chatmessage.modele.Phonebook;
import com.smag.chatmessage.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonebookRepository extends JpaRepository<Phonebook,Integer> {

    public List findAll();

    public Phonebook findPhonebookByIdPhonebook (int id);

    public Phonebook findByUserByProprietaire(User user);

    public Phonebook findByUserByProprietaire_IdUser(String id);

}
