package com.PayMyBuddy.paymybuddy.controller;

import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User newUser){
        return userService.addUser(newUser);
    }

    @PostMapping(value = "/updateUser/{id}")
    public User updateUser(@RequestBody User userToUpdate, @PathVariable int id){
        return userService.updateUser(userToUpdate, id);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
    }
}
