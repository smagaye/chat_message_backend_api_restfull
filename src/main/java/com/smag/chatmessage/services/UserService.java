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
        userRepository.save(user);
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

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
}
