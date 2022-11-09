package com.PayMyBuddy.paymybuddy.service;
import com.PayMyBuddy.paymybuddy.model.Transaction;
import com.PayMyBuddy.paymybuddy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(Integer userId) {
        return transactionRepository.findAll();
    }

    public Transaction addTransaction(Transaction newTransaction) {
        return transactionRepository.save(newTransaction);
    }

    public void deleteTransactionById(Integer id) {
        transactionRepository.deleteById(id);
    }
}
