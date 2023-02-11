package com.PayMyBuddy.paymybuddy.controller;

import com.PayMyBuddy.paymybuddy.model.Transfer;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.service.TransactionService;
import com.PayMyBuddy.paymybuddy.service.TransferService;
import com.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    @GetMapping("/contact")
    public String contactPage(Model model, Principal principal) {
        User user = userService.getUserByMail(principal.getName());
        List<User> contacts = userService.getUsers();
        List<User> friends = userService.getFriendList(user.getIdUser());
        for (User friend : friends) {
            contacts.remove(friend);
        }
        contacts.remove(user);
        model.addAttribute("contacts", contacts);
        return "contact.html";
    }

    @PostMapping("/contact")
    public String searchUsers(@RequestBody String query, Model model, Principal principal) {
        User user = userService.getUserByMail(principal.getName());
        try {
            query = URLDecoder.decode(query, "UTF-8");
            int index = query.indexOf("=", query.indexOf("=") + 1);
            query = query.substring(index + 1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<User> contacts = userService.searchUserByMail(query);
        contacts.remove(user);
        model.addAttribute("contacts", contacts);
        return "contact.html";
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    @PostMapping(value = "/updateUser")
    public String updateUser(@ModelAttribute("user") User user, Principal principal) {
        User userToUpdate = userService.getUserByMail(principal.getName());
        userService.updateUser(user, userToUpdate.getIdUser());
        return "redirect:/profile";
    }

    @PostMapping(value = "/updateBalance/{id}")
    public User updateBalanceUser(@RequestBody double amount, @RequestBody String action, @PathVariable int id) {
        return userService.updateBalanceUser(id, amount, action);
    }

    @PostMapping(value = "/addFriend/{userId}")
    public RedirectView addFriend(@PathVariable int userId, Principal principal) {
        User user = userService.getUserByMail(principal.getName());
        int firstUser = user.getIdUser();
        userService.addFriend(firstUser, userId);
        return new RedirectView("/contact");
    }

    @PostMapping("/signin")
    public String signinPage(@ModelAttribute User newUser) {
        User newUseradded = userService.addUser(newUser);
        if (newUseradded.getEmail() != null) {
            return "redirect:/login";
        } else {
            return "redirect:/signin";
        }
    }
}
