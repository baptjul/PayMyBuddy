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

    @GetMapping(value = "/friends/{id}")
    public List<User> getFriendList(@PathVariable int id){
        return userService.getFriendList(id);
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User newUser){
        return userService.addUser(newUser);
    }

    @PostMapping(value = "/updateUser/{id}")
    public User updateUser(@RequestBody User userToUpdate, @PathVariable int id){
        return userService.updateUser(userToUpdate, id);
    }

    @PostMapping(value = "/updateBalance/{id}")
    public User updateBalanceUser(@RequestBody double amount, @RequestBody String action, @PathVariable int id){
        return userService.updateBalanceUser(id, amount, action);
    }

    @PostMapping(value = "/addFriend/{firstId}/{secondId}")
    public User addFriend(@PathVariable int firstId, @PathVariable int secondId){
        return userService.addFriend(firstId, secondId);
    }
}
