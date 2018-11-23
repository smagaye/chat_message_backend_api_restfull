package com.smag.chatmessage.controller;

import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RepositoryRestController
@RestController
public class APIadmin {
    @Autowired
    UserService userService;
    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity<?> create(@Valid @RequestBody List<User> users) {
        List<User> errorUser =new ArrayList<>();
        if(users!=null)
            for(User user : users){
                try{
                    User userFound = userService.findByEmailOrPhone(user.getEmail(),user.getPhone());
                    if(userFound==null){
                        userService.save(user);
                    }else{
                        errorUser.add(user);
                    }
                }catch (Exception e){
                    errorUser.add(user);
                }
            }
        if(errorUser.isEmpty()) return new ResponseEntity<>(errorUser, HttpStatus.OK);

        else return new ResponseEntity(errorUser,HttpStatus.CONFLICT);
    }

    @RequestMapping(path="/addUser")
    private ResponseEntity<?> addUser(@RequestBody User user) {
        User userFound = userService.findByEmailOrPhone(user.getEmail(),user.getPhone());
        if(userFound==null){
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }

    }

    @GetMapping(value="/users")
    private @ResponseBody ResponseEntity<Object> findUserByParameters (
            @RequestParam(required=false) String surname,
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String email,
            @RequestParam(required=false) String phone,
            @RequestParam(required=false) String profile){

        if(email!=null){
            User user =userService.findByEmail(email);
            if (user != null) {
                return  new ResponseEntity(user, HttpStatus.OK);
            }
                return new ResponseEntity(user, HttpStatus.NOT_FOUND);
        }else if(name!=null){
            List<User> users =userService.findByName(name);
            if (users != null) {
                return  new ResponseEntity(users, HttpStatus.OK);
            }
                return new ResponseEntity(users, HttpStatus.NOT_FOUND);

        }else if(surname!=null){
            List<User> users =userService.findBySurname(surname);
            if ((users != null)) {
                return  new ResponseEntity(users, HttpStatus.OK);
            }
                return new ResponseEntity(users, HttpStatus.NOT_FOUND);
        }else if(phone!=null){
           User user =userService.findByPhone(phone);
            if (user != null) {
                return  new ResponseEntity(user, HttpStatus.OK);
            }
                return new ResponseEntity(user, HttpStatus.NOT_FOUND);

        }else if(profile!=null){
           User user =userService.findByProfile(profile);
            if (user != null) {
                return  new ResponseEntity(user, HttpStatus.OK);
            }
                return new ResponseEntity(user, HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping(value="/users/{id}")
    private User getUserById(@PathVariable("id")  String id){
        return userService.findByIdUser(id);
    }

    @PutMapping(value="/updateUser")
    private ResponseEntity<User> updateUser (@RequestBody User user) {
        User userFound = userService.findByEmailOrPhone(user.getEmail(),user.getPhone());

        if(userFound!=null){
            userFound.formatToUpdate(user);
            userService.save(user);
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(user,HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value="/users")
    private void deleteAllUsers(){
        userService.deleteAllUsers();
    }

    @DeleteMapping(value="/users/{id}")
    private String deleteUserById(@PathVariable("id")  String id){
         if(userService.findByIdUser(id)!=null)userService.deleteById(id);
        return "Deleted";
    }

}
