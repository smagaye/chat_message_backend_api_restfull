package com.smag.chatmessage.services;

import com.smag.chatmessage.helper.GenerateCode;
import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Transactional
    public void save(User user) {
        user.setIdUser(GenerateCode.clefUTC("USR"));
        userRepository.saveAndFlush(user);
    }

    public List<User> getAllUsers(){
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

    public List<User> findBySurname(String surname){ return userRepository.findBySurname(surname);}

    public List<User> findByName(String name){return userRepository.findByName(name);}

    public User findByProfile(String profile){return userRepository.findByProfile(profile);}

    public List<User> findAllContactById(Set listPhonebook){return userRepository.findByIdUserIn(listPhonebook);}

    public User findByEmailOrPhone(String email,String phone){return userRepository.findByEmailOrPhone(email,phone);}

    @Transactional
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void deleteById(String id) {
        userRepository.deleteByIdUser(id);
    }
}
