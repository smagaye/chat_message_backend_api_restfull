package com.smag.chatmessage.repositories;

import com.smag.chatmessage.modele.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    public User findByIdUser(String id);

    public User findByEmail(String id);

    public User findByPhone(String id);

    public  void deleteAll();

}
