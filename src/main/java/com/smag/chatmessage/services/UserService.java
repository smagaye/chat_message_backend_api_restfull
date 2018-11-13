package com.smag.chatmessage.services;

import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user) {
        userRepository.saveAndFlush(user);
    }

    public Iterable<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public User findByIdUser(String id){
       return userRepository.findByIdUser(id);
    }

    public User findByEmail(String id){
        return userRepository.findByEmail(id);
    }

    public User findByPhone(String id){
        return userRepository.findByPhone(id);
    }

    public User findByEmailOrPhone(String email,String phone){return userRepository.findByEmailOrPhone(email,phone);}

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }


    public void deleteById(String id) {
        userRepository.deleteByIdUser(id);
    }
}
