package com.smag.chatmessage.api;

import com.smag.chatmessage.modele.User;
import com.smag.chatmessage.modele.UserRest;
import com.smag.chatmessage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
@RestController
public class AdminAPI {
    @Autowired
    UserService userService;
    @PostMapping(value="/admin/addUsers")
    private ResponseEntity addUsers(@Valid @RequestBody List<User> users) {
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
        if(errorUser.isEmpty()) return new ResponseEntity(errorUser, HttpStatus.OK);

        else return new ResponseEntity(errorUser,HttpStatus.CONFLICT);
    }

    @PostMapping(path="/admin/addUser")
    private ResponseEntity addUser(@RequestBody User user) {
        User userFound = userService.findByEmailOrPhone(user.getEmail(),user.getPhone());
        if(userFound==null){
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }

    }

    @GetMapping(value="/admin/users")
    private @ResponseBody ResponseEntity findUserByParameters (
            @RequestParam(required=false) String surname,
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String email,
            @RequestParam(required=false) String phone,
            @RequestParam(required=false) String profile){

        if(email!=null){
            UserRest user =userService.findByEmail(email);
            if (user != null) {
                return  new ResponseEntity<>(user, HttpStatus.OK);
            }
                return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }else if(name!=null){
            List<UserRest> users =userService.findByName(name);
            if (users != null) {
                return  new ResponseEntity<>(users, HttpStatus.OK);
            }
                return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);

        }else if(surname!=null){
            List<UserRest> users =userService.findBySurname(surname);
            if ((users != null)) {
                return  new ResponseEntity<>(users, HttpStatus.OK);
            }
                return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        }else if(phone!=null){
           UserRest user =userService.findByPhone(phone);
            if (user != null) {
                return  new ResponseEntity<>(user, HttpStatus.OK);
            }
                return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);

        }else if(profile!=null){
           UserRest user =userService.findByProfile(profile);
            if (user != null) {
                return  new ResponseEntity<>(user, HttpStatus.OK);
            }
                return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PutMapping(value="/admin/updateUser")
    private ResponseEntity updateUser (@RequestBody User user) {
        User userFound = userService.findByEmailOrPhone(user.getEmail(),user.getPhone());
        if(userFound!=null){
            userFound.formatToUpdate(user);
            userService.save(user);
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(user,HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value="/admin/users")
    private void deleteAllUsers(){
        userService.deleteAllUsers();
    }

    @DeleteMapping(value="/admin/users/{id}")
    private String deleteUserById(@PathVariable("id")  String id){
         if(userService.findByIdUser(id)!=null)userService.deleteById(id);
        return "Deleted";
    }

}
