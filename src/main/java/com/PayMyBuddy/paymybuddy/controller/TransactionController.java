package com.PayMyBuddy.paymybuddy.controller;

import com.PayMyBuddy.paymybuddy.model.Transaction;
import com.PayMyBuddy.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/userTransaction/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable int userId){
        return transactionService.getUserTransactions(userId);
    }

    @PostMapping(value = "/addTransaction")
    public Transaction addTransaction(@RequestBody Transaction newTransaction){
        return transactionService.addTransaction(newTransaction);
    }

}
