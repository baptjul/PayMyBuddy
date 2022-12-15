package com.PayMyBuddy.paymybuddy.service;

import com.PayMyBuddy.paymybuddy.model.Transaction;
import com.PayMyBuddy.paymybuddy.model.Transfer;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.repository.TransferRepository;
import com.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransferServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    TransferService transferService;
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        userRepository.flush();
        transferRepository.deleteAll();
        transferRepository.flush();
    }

    @Test
    public void addTransferTest() {
        User firstNewUser = new User();
        firstNewUser.setBalance(200);
        firstNewUser.setEmail("johndoe@gmail.com");
        firstNewUser.setPassword("12345678910");
        firstNewUser.setFirst_name("john");
        firstNewUser.setLast_name("doe");
        firstNewUser.setUser_name("johndoe1");
        User firstuserAdded = userService.addUser(firstNewUser);

        Transfer newTransfer = new Transfer();
        newTransfer.setAmount(100);
        newTransfer.setType("credit");
        newTransfer.setIban("123456799");
        newTransfer.setPayMyBuddyAccount(firstuserAdded);

        assertEquals(newTransfer, transferService.addTransfer(newTransfer));
    }

    @Test
    public void getUserTransferTest() {
        User firstNewUser = new User();
        firstNewUser.setBalance(200);
        firstNewUser.setEmail("johndoe@gmail.com");
        firstNewUser.setPassword("12345678910");
        firstNewUser.setFirst_name("john");
        firstNewUser.setLast_name("doe");
        firstNewUser.setUser_name("johndoe1");
        User firstuserAdded = userService.addUser(firstNewUser);

        Transfer newTransfer = new Transfer();
        newTransfer.setAmount(100);
        newTransfer.setType("credit");
        newTransfer.setIban("123456799");
        newTransfer.setPayMyBuddyAccount(firstuserAdded);
        Transfer savedTransfer = transferService.addTransfer(newTransfer);

        assertEquals(savedTransfer.getId_transfer(), transferService.getTransferById(savedTransfer.getId_transfer()).getId_transfer());
        assertEquals(savedTransfer.getId_transfer(), transferService.getUserTransfer(firstuserAdded.getIdUser()).get(0).getId_transfer());
    }
}
