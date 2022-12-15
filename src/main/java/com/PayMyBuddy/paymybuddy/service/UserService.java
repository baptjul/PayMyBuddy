package com.PayMyBuddy.paymybuddy.service;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User addUser(User user) {
        List<User> userList = getUsers();
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        for (User existingUser : userList){
            if (existingUser.getEmail().equals(user.getEmail())) {
                return null;
            }
        }

        user.setCreation_date(date);
        return userRepository.save(user);
    }

    public User updateUser(@NotNull User user, int id) {
        User userToUpdate = getUserById(id);

        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setFirst_name(user.getFirst_name());
        userToUpdate.setLast_name(user.getLast_name());
        userToUpdate.setUser_name(user.getUser_name());

        return userRepository.save(userToUpdate);
    }

    public User addFriend(int firstUser, int secondUser) {
        User firstFriend = getUserById(firstUser);
        User secondFriend = getUserById(secondUser);
        List<User> friendList = firstFriend.getFriendlist();
        if (checkExistingFriends(firstUser, secondUser) == 1) {
            return null;
        } else {
            friendList.add(secondFriend);
            firstFriend.setFriendlist(friendList);
            return userRepository.save(firstFriend);
        }
    }

    public int checkExistingFriends(int firstUser, int secondUser) {
        return userRepository.findFriend(firstUser, secondUser);
    }

    public List<User> getFriendList(int id) {
        User user = getUserById(id);
        return user.getFriendlist();
    }

    public void updateBalanceTransaction(User sender, User receiver, double transaction) {
        double balanceTransmitter = sender.getBalance();
        sender.setBalance(balanceTransmitter - transaction);

        double balanceReceiver = receiver.getBalance();
        receiver.setBalance(balanceReceiver + transaction);

        userRepository.save(sender);
        userRepository.save(receiver);
    }

    public User updateBalanceUser(int id, double amount, String type) {
        User userToUpdate = getUserById(id);
        if (Objects.equals(type, "debit")) {
            userToUpdate.setBalance(userToUpdate.getBalance() - amount);
            return userRepository.save(userToUpdate);
        } else if (Objects.equals(type, "credit")) {
            userToUpdate.setBalance(userToUpdate.getBalance() + amount);
            return userRepository.save(userToUpdate);
        }
        return null;
    }
}
