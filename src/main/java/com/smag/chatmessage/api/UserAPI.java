package com.smag.chatmessage.api;

import com.smag.chatmessage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAPI {
    @Autowired
    UserService userService;

    @GetMapping(value="/user/{id}")
    private ResponseEntity getUserByIdUser(@PathVariable("id")  String id){
        return new ResponseEntity(userService.findById(id), HttpStatus.OK);
    }

}
