package com.smag.chatmessage.controller;

import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RestController
@ResponseBody
public class APIadmin {
    @Autowired
    UserService userService;

    @RequestMapping("/users")
    Iterable<User> Users (){
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users/id/{id}", method = RequestMethod.GET)
    User getUserById(@PathVariable("id")  String id){
        return userService.findByIdUser(id);
    }

    @RequestMapping(value="/users/email/{email}", method = RequestMethod.GET)
    User getUserByEmail(@PathVariable("email")  String email){
        return userService.findByEmail(email);
    }

    @RequestMapping(value="/users/phone/{phone}", method = RequestMethod.GET)
    User getUserByPhone(@PathVariable("phone")  String phone){
        return userService.findByPhone(phone);
    }

    @RequestMapping(value="/users", method = RequestMethod.DELETE)
    void deleteAllUsers(){
        userService.deleteAllUsers();
    }



}
