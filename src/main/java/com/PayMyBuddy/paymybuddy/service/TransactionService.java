package com.PayMyBuddy.paymybuddy.service;
import com.PayMyBuddy.paymybuddy.constants.Fare;
import com.PayMyBuddy.paymybuddy.model.Transaction;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.repository.TransactionRepository;
import com.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    UserService userService;
    @Autowired
    TransactionRepository transactionRepository;

    public double applyFee(double transaction) {
        return transaction + ((transaction * Fare.TRANSACTION_FARE) / 100);
    }

    public List<Transaction> getUserTransactions(int userId) {
        User user = userService.getUserById(userId);
        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(user.getTransmittedTransactions());
        allTransactions.addAll(user.getReceivedTransactions());
        System.out.println(allTransactions);
        return allTransactions;
    }

    public Transaction addTransaction(Transaction newTransaction) {
        User sender = newTransaction.getUserTransmitter();
        User receiver = newTransaction.getUserReceiver();

        if (sender.getBalance() > newTransaction.getTransaction() && userService.checkExistingFriends(sender.getIdUser(), receiver.getIdUser()) == 1) {
            Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            newTransaction.setTransaction_date(date);
            userService.updateBalanceTransaction(sender, receiver, newTransaction.getTransaction());

            newTransaction.setTransaction(applyFee(newTransaction.getTransaction()));

            System.out.println(newTransaction);
            return transactionRepository.save(newTransaction);
        }
        return null;
    }
}
