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

    @GetMapping(value = "/transaction/{userId}")
    public List<Transaction> getTransactions(@PathVariable int userId){
        return transactionService.getTransactions(userId);
    }

    @PostMapping(value = "/addTransaction")
    public Transaction addTransaction(@RequestBody Transaction newTransaction){
        return transactionService.addTransaction(newTransaction);
    }

    @DeleteMapping(value = "/deleteTransaction/{id}")
    public void deleteTransactionById(@PathVariable int id){
        transactionService.deleteTransactionById(id);
    }
}
