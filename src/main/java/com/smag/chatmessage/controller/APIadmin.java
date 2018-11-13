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

    @RequestMapping(value="/users/{id}", method = RequestMethod.GET)
    User getUserById(@PathVariable("id")  String id){
        return userService.findByIdUser(id);
    }

    @RequestMapping(value="/users/emails/{email}", method = RequestMethod.GET)
    User getUserByEmail(@PathVariable("email")  String email){
        return userService.findByEmail(email);
    }

    @RequestMapping(value="/users/phones/{phone}", method = RequestMethod.GET)
    User getUserByPhone(@PathVariable("phone")  String phone){
        return userService.findByPhone(phone);
    }

    @RequestMapping(value="/users", method = RequestMethod.DELETE)
    void deleteAllUsers(){
        userService.deleteAllUsers();
    }

    @RequestMapping(value="/users/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteUserById(@PathVariable("id")  String id){
         if(userService.findByIdUser(id)!=null)userService.deleteById(id);
        return "Deleted";
    }

    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public @ResponseBody String addNewUser (@RequestParam(required=false) String id,
                                            @RequestParam String surname,
                                            @RequestParam(required=false) String name,
                                            @RequestParam(required=false)  String email,
                                            @RequestParam String phone,
                                            @RequestParam String password,
                                            @RequestParam(required=false) String profile) {
        User user;
        User userFound = userService.findByEmailOrPhone(email,phone);

        if(userFound==null){
            user = new User();
            user.setIdUser(id.trim());
            user.setSurname(surname.trim());
            user.setName(name.trim());
            user.setEmail(email.trim());
            user.setPhone(phone.trim());
            user.setPassword(password.trim());
            user.setProfile(profile.trim());
            userService.save(user);
            return "{status: \"User added\"}";
        }else{
            return "{status: \"User already exists\"}";
        }
    }

    @RequestMapping(value="/updateUser", method = RequestMethod.PUT)
    public @ResponseBody String updateUser (
                                            @RequestParam(required=false) String surname,
                                            @RequestParam(required=false) String name,
                                            @RequestParam(required=false) String email,
                                            @RequestParam(required=false) String phone,
                                            @RequestParam(required=false) String password,
                                            @RequestParam(required=false) String profile) {
        User userFound = userService.findByEmailOrPhone(email,phone);

        if(userFound!=null){
            User user = new User();
            user.setSurname(surname.trim());
            user.setName(name.trim());
            user.setEmail(email.trim());
            user.setPhone(phone.trim());
            user.setPassword(password.trim());
            user.setProfile(profile.trim());
            userFound.formatToUpdate(user);
            userService.save(user);
            return "{status: \"Updated\"}";
        }else{
            return "{status: \"User doesn't\"}";
        }
    }

}
