package com.smag.chatmessage.repositories;

import com.smag.chatmessage.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findAll();

    public User findByIdUser(String id);

    public User findByEmail(String email);

    public User findByPhone(String phone);

    public void deleteAll();

    public void deleteByIdUser(String id);

    public void deleteByEmail(String email);

    public void deleteByPhone(String phone);

    public User findByEmailOrPhone(String email, String phone);


}
