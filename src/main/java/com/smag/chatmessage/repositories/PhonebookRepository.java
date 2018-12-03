package com.smag.chatmessage.repositories;

import com.smag.chatmessage.modele.Phonebook;
import com.smag.chatmessage.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PhonebookRepository extends JpaRepository<Phonebook,Integer> {

    public List findAll();

    public Phonebook findPhonebookByIdPhonebook (int id);

    public Phonebook findByOwner(String id);

    @Transactional
    public void delete(Phonebook phonebook);

    @Transactional
    public void deleteByIdPhonebook(String id);

    @Query("SELECT p FROM User u , Phonebook p where p.owner=u.idUser and p.owner=:idUser")
    public List<Phonebook>  recapePhonebookByEmailUserOrPhoneUser(@Param("idUser") String idUser);


}
