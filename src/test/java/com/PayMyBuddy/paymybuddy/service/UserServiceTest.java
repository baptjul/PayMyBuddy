package com.PayMyBuddy.paymybuddy.service;

import com.PayMyBuddy.paymybuddy.constants.DBqueries;
import com.PayMyBuddy.paymybuddy.model.Transaction;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        userRepository.flush();
    }

    @Test
    public void addUserTest() {
        User newUser = new User();
        newUser.setBalance(200);
        newUser.setEmail("johndoe@gmail.com");
        newUser.setPassword("12345678910");
        newUser.setFirst_name("john");
        newUser.setLast_name("doe");
        newUser.setUser_name("johndoe1");
        User userAdded = userService.addUser(newUser);

        assertEquals("johndoe@gmail.com", userService.getUserById(userAdded.getIdUser()).getEmail());
    }

    @Test
    void updateServiceMethodTest() {
        User newUser = new User();
        newUser.setBalance(200);
        newUser.setEmail("johndoe@gmail.com");
        newUser.setPassword("12345678910");
        newUser.setFirst_name("john");
        newUser.setLast_name("doe");
        newUser.setUser_name("johndoe1");
        User userAdded = userService.addUser(newUser);
        User savedUser = userService.getUserById(userAdded.getIdUser());

        User updatedUser = new User();
        updatedUser.setBalance(200);
        updatedUser.setEmail("doejohn@gmail.com");
        updatedUser.setPassword("12345678910");
        updatedUser.setFirst_name("john");
        updatedUser.setLast_name("doe");
        updatedUser.setUser_name("johndoe1");
        User updateUser = userService.updateUser(updatedUser, savedUser.getIdUser());

        assertEquals("doejohn@gmail.com", userService.updateUser(updatedUser, savedUser.getIdUser()).getEmail());
    }

    @Test
    public void addFriendTest() {
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

        assertEquals("johndoe@gmail.com", userService.addFriend(firstuserAdded.getIdUser(), seconduserAdded.getIdUser()).getEmail());
    }

    @Test
    public void getFriendListTest() {
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

        int idFriend = userService.getFriendList(firstuserAdded.getIdUser()).get(0).getIdUser();
        assertEquals(seconduserAdded.getIdUser(), idFriend);
    }

    @Test
    public void updateBalanceUserTest() {
        User newUser = new User();
        newUser.setBalance(200);
        newUser.setEmail("johndoe@gmail.com");
        newUser.setPassword("12345678910");
        newUser.setFirst_name("john");
        newUser.setLast_name("doe");
        newUser.setUser_name("johndoe1");
        User userAdded = userService.addUser(newUser);

        assertEquals(500, userService.updateBalanceUser(userAdded.getIdUser(), 300, "credit").getBalance());
        assertEquals(200, userService.updateBalanceUser(userAdded.getIdUser(), 300, "debit").getBalance());
    }

    @Test
    public void updateBalanceWrongTypeTest() {
        User newUser = new User();
        newUser.setBalance(200);
        newUser.setEmail("johndoe@gmail.com");
        newUser.setPassword("12345678910");
        newUser.setFirst_name("john");
        newUser.setLast_name("doe");
        newUser.setUser_name("johndoe1");
        User userAdded = userService.addUser(newUser);

        assertNull(userService.updateBalanceUser(userAdded.getIdUser(), 300, "none"));
    }


    @Test
    public void addAlreadyFriendTest() {
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

        assertNull(userService.addFriend(firstuserAdded.getIdUser(), seconduserAdded.getIdUser()));
    }
}
