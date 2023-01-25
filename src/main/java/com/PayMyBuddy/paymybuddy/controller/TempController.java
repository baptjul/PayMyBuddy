package com.PayMyBuddy.paymybuddy.controller;

import com.PayMyBuddy.paymybuddy.model.Transaction;
import com.PayMyBuddy.paymybuddy.model.Transfer;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.service.TransactionService;
import com.PayMyBuddy.paymybuddy.service.TransferService;
import com.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.text.AttributedString;
import java.util.List;

@Controller
public class TempController {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/home")
    public String homePage() {
        return "index.html";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        List<User> contacts = userService.getUsers();
        System.out.println(contacts);
        model.addAttribute("contacts", contacts);
        return "contact.html";
    }
}