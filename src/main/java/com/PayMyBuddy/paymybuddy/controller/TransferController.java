package com.PayMyBuddy.paymybuddy.controller;

import com.PayMyBuddy.paymybuddy.model.Transfer;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.service.TransferService;
import com.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Controller
public class TransferController {

    @Autowired
    private TransferService transferService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/transferBank/{id}")
    public Transfer getTransferById(@PathVariable int id){
        return transferService.getTransferById(id);
    }

    @GetMapping(value = "/userTransfer/{id}")
    public List<Transfer> getUserTransfer(@PathVariable int id) {
        return transferService.getUserTransfer(id);
    }

    @PostMapping(value = "/profile")
    public String addTransfer(@ModelAttribute Transfer newPayMyBuddy, Principal principal) {
        User user = userService.getUserByMail(principal.getName());
        newPayMyBuddy.setPayMyBuddyAccount(user);
        transferService.addTransfer(newPayMyBuddy);
        return "redirect:/profile";
    }
}
