package com.smag.chatmessage.repositories;

import com.smag.chatmessage.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findAll();

    public User findByIdUser(String id);

    public User findByEmail(String email);

    public User findByPhone(String phone);

    public List<User> findByIdUserIn(List<String> listIdUser);

    public List<User> findBySurname(String surname);

    public List<User> findByName(String name);

    public User findByProfile(String profile);

    public User findBySurnameOrNameOrEmailOrPhoneOrProfile(String surname, String name, String email, String phone, String profile);


    @Transactional
    public void deleteAll();

    @Transactional
    public void deleteByIdUser(String id);

    @Transactional
    public void deleteByEmail(String email);

    @Transactional
    public void deleteByPhone(String phone);

    @Transactional
    public User findByEmailOrPhone(String email, String phone);


}
