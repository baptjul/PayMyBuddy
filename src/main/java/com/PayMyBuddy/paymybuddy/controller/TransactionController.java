package com.PayMyBuddy.paymybuddy.controller;

import com.PayMyBuddy.paymybuddy.model.Transaction;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.service.TransactionService;
import com.PayMyBuddy.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/userTransaction/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable int userId){
        return transactionService.getUserTransactions(userId);
    }

    @PostMapping(value = "/transfer")
    public String addTransaction(@ModelAttribute Transaction newTransaction, Model model, Principal principal){
        model.addAttribute("transactions", newTransaction);
        User user = userService.getUserByMail(principal.getName());
        int id = user.getIdUser();
        newTransaction.setUserTransmitter(user);
        List<Transaction> transactions = transactionService.getUserTransactions(id);
        transactions.add(newTransaction);
        transactionService.addTransaction(newTransaction);
        return "redirect:/transfer";
    }

    @GetMapping("/transfer")
    public String transferPage(Model model, Principal principal) {
        User user = userService.getUserByMail(principal.getName());
        int id = user.getIdUser();
        Transaction newTransaction = new Transaction();
        List<Transaction> transactions = transactionService.getUserTransactions(id);
        List<User> friends = userService.getFriendList(id);
        model.addAttribute("newTransaction", newTransaction);
        model.addAttribute("transactions", transactions);
        model.addAttribute("friends", friends);
        return "transfer.html";
    }
}
