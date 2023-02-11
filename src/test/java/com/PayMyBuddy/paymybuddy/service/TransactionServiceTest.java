package com.PayMyBuddy.paymybuddy.service;

import com.PayMyBuddy.paymybuddy.model.Transaction;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.repository.TransactionRepository;
import com.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        userRepository.flush();
        transactionRepository.deleteAll();
        transactionRepository.flush();
    }

    @Test
    public void addTransactionTest() {
        User firstNewUser = new User();
        firstNewUser.setBalance(200);
        firstNewUser.setEmail("johndoe@gmail.com");
        firstNewUser.setPassword("12345678910");
        firstNewUser.setFirst_name("john");
        firstNewUser.setLast_name("doe");
        firstNewUser.setUser_name("johndoe1");
        User firstuserAdded = userService.addUser(firstNewUser);

        User secondNewUser = new User();
        secondNewUser.setBalance(200);
        secondNewUser.setEmail("janedoe@gmail.com");
        secondNewUser.setPassword("12345678910");
        secondNewUser.setFirst_name("jane");
        secondNewUser.setLast_name("doe");
        secondNewUser.setUser_name("janedoe2");
        User seconduserAdded = userService.addUser(secondNewUser);
        userService.addFriend(firstuserAdded.getIdUser(), seconduserAdded.getIdUser());

        Transaction newTransactions = new Transaction();
        newTransactions.setTransaction(100);
        newTransactions.setUserTransmitter(firstuserAdded);
        newTransactions.setUserReceiver(seconduserAdded);
        System.out.println(newTransactions);

        assertEquals(newTransactions, transactionService.addTransaction(newTransactions));
    }

    @Test
    public void getUserTransactionsTest() {
        User firstNewUser = new User();
        firstNewUser.setBalance(200);
        firstNewUser.setEmail("johndoe@gmail.com");
        firstNewUser.setPassword("12345678910");
        firstNewUser.setFirst_name("john");
        firstNewUser.setLast_name("doe");
        firstNewUser.setUser_name("johndoe1");
        User firstuserAdded = userService.addUser(firstNewUser);

        User secondNewUser = new User();
        secondNewUser.setBalance(200);
        secondNewUser.setEmail("janedoe@gmail.com");
        secondNewUser.setPassword("12345678910");
        secondNewUser.setFirst_name("jane");
        secondNewUser.setLast_name("doe");
        secondNewUser.setUser_name("janedoe2");
        User seconduserAdded = userService.addUser(secondNewUser);
        userService.addFriend(firstuserAdded.getIdUser(), seconduserAdded.getIdUser());

        Transaction newTransactions = new Transaction();
        newTransactions.setTransaction(100);
        newTransactions.setUserTransmitter(firstuserAdded);
        newTransactions.setUserReceiver(seconduserAdded);
        Transaction transactionAdded = transactionService.addTransaction(newTransactions);

        assertEquals(transactionAdded.getIdTransaction(), transactionService.getUserTransactions(firstuserAdded.getIdUser()).get(0).getIdTransaction());
    }

    @Test
    public void failedAmountTransactionTest() {
        User firstNewUser = new User();
        firstNewUser.setBalance(200);
        firstNewUser.setEmail("johndoe@gmail.com");
        firstNewUser.setPassword("12345678910");
        firstNewUser.setFirst_name("john");
        firstNewUser.setLast_name("doe");
        firstNewUser.setUser_name("johndoe1");
        User firstuserAdded = userService.addUser(firstNewUser);

        User secondNewUser = new User();
        secondNewUser.setBalance(200);
        secondNewUser.setEmail("janedoe@gmail.com");
        secondNewUser.setPassword("12345678910");
        secondNewUser.setFirst_name("jane");
        secondNewUser.setLast_name("doe");
        secondNewUser.setUser_name("janedoe2");
        User seconduserAdded = userService.addUser(secondNewUser);
        userService.addFriend(firstuserAdded.getIdUser(), seconduserAdded.getIdUser());

        Transaction newTransactions = new Transaction();
        newTransactions.setTransaction(300);
        newTransactions.setUserTransmitter(firstuserAdded);
        newTransactions.setUserReceiver(seconduserAdded);

        assertNull(transactionService.addTransaction(newTransactions));
    }

    @Test
    public void failedFriendsTransactionTest() {
        User firstNewUser = new User();
        firstNewUser.setBalance(200);
        firstNewUser.setEmail("johndoe@gmail.com");
        firstNewUser.setPassword("12345678910");
        firstNewUser.setFirst_name("john");
        firstNewUser.setLast_name("doe");
        firstNewUser.setUser_name("johndoe1");
        User firstuserAdded = userService.addUser(firstNewUser);

        User secondNewUser = new User();
        secondNewUser.setBalance(200);
        secondNewUser.setEmail("janedoe@gmail.com");
        secondNewUser.setPassword("12345678910");
        secondNewUser.setFirst_name("jane");
        secondNewUser.setLast_name("doe");
        secondNewUser.setUser_name("janedoe2");
        User seconduserAdded = userService.addUser(secondNewUser);

        Transaction newTransactions = new Transaction();
        newTransactions.setTransaction(100);
        newTransactions.setUserTransmitter(firstuserAdded);
        newTransactions.setUserReceiver(seconduserAdded);

        assertNull(transactionService.addTransaction(newTransactions));
    }
}
