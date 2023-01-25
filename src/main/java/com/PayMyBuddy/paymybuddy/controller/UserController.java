package com.PayMyBuddy.paymybuddy.controller;

import com.PayMyBuddy.paymybuddy.model.Transfer;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.service.TransactionService;
import com.PayMyBuddy.paymybuddy.service.TransferService;
import com.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/friends/{id}")
    public List<User> getFriendList(@PathVariable int id) {
        return userService.getFriendList(id);
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        Transfer newPayMyBuddy = new Transfer();
        User user = userService.getUserByMail(principal.getName());
        int id = user.getIdUser();
        List<Transfer> transfers = transferService.getUserTransfer(id);
        List<User> friends = userService.getFriendList(id);
        model.addAttribute("friends", friends);
        model.addAttribute("user", user);
        model.addAttribute("transfers", transfers);
        model.addAttribute("newPayMyBuddy", newPayMyBuddy);
        return "profile.html";
    }

    @GetMapping("/login")
    public String logPage(Model model) {
        model.addAttribute("user", new User());
        return "login.html";
    }

    @GetMapping("/signin")
    public String signPage(Model model) {
        model.addAttribute("newUser", new User());
        return "signin.html";
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    @PostMapping(value = "/updateUser/{id}")
    public User updateUser(@RequestBody User userToUpdate, @PathVariable int id) {
        return userService.updateUser(userToUpdate, id);
    }

    @PostMapping(value = "/updateBalance/{id}")
    public User updateBalanceUser(@RequestBody double amount, @RequestBody String action, @PathVariable int id) {
        return userService.updateBalanceUser(id, amount, action);
    }

    @PostMapping(value = "/addFriend/{firstId}/{secondId}")
    public User addFriend(@PathVariable int firstId, @PathVariable int secondId) {
        return userService.addFriend(firstId, secondId);
    }

    @PostMapping("/signin")
    public String signinPage(@ModelAttribute User newUser) {
        User newUseradded = userService.addUser(newUser);
        if (newUseradded.getEmail() != null) {
            //userService.loadUserByUsername(newUseradded.getEmail());
            return "redirect:/home";
        } else {
            return "redirect:/signin";
        }
    }
}
