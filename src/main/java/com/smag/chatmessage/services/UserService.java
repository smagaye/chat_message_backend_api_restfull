package com.smag.chatmessage.services;

import com.smag.chatmessage.helper.GenerateCode;
import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.modele.UserRest;
import com.smag.chatmessage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<UserRest> getAllUsers(){
        return convertToUserRest(userRepository.findAll());
    }

    public UserRest findById(String id){
       return userRepository.findByIdUser(id).converToValidUserRest();
    }

    public User findByIdUser(String id){
        return userRepository.findByIdUser(id);
    }

    public UserRest findByEmail(String id){
        return userRepository.findByEmail(id).converToValidUserRest();
    }

    public UserRest findByPhone(String id){
        return userRepository.findByPhone(id).converToValidUserRest();
    }

    public List<UserRest> findBySurname(String surname){ return convertToUserRest(userRepository.findBySurname(surname));}

    public List<UserRest> findByName(String name){return convertToUserRest(userRepository.findByName(name));}

    public UserRest findByProfile(String profile){return userRepository.findByProfile(profile).converToValidUserRest();}

    public List<UserRest> findAllContactById(List listPhonebook){return convertToUserRest(userRepository.findByIdUserIn(listPhonebook));}

    public User findByEmailOrPhone(String email,String phone){return userRepository.findByEmailOrPhone(email,phone);}

    public List<User> findUsers(List<String> list){
        return userRepository.findByIdUserIn(list);
    }

    public List<User> findUsers(String id1,String id2){
        List<String> list = new ArrayList<>();
        list.add(id1);
        list.add(id2);
        return userRepository.findByIdUserIn(list);
    }

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

    public static List<UserRest> convertToUserRest(List<User> users){
        List<UserRest> userRestList=null;
        if(users !=null){
             userRestList = new ArrayList<>();
            for (User user : users){
                userRestList.add(user.converToValidUserRest());
            }
        }
        return userRestList;
    }
}
