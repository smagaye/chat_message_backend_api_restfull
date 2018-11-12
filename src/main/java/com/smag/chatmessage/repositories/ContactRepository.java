package com.smag.chatmessage.repositories;

import com.smag.chatmessage.modele.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {

    @Override
    List findAll();

}
