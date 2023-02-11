package com.PayMyBuddy.paymybuddy.service;
import com.PayMyBuddy.paymybuddy.constants.Fare;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByMail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public User getUserByMail(String email) {
        return userRepository.findByMail(email);
    }

    public List<User> searchUserByMail(String email) {
        return userRepository.searchByMail(email);
    }

    public User addUser(User user) {
        List<User> userList = getUsers();
        LocalDate localDate = LocalDate.now();
        Date sqlDate = java.sql.Date.valueOf(localDate);
        for (User existingUser : userList){
            if (existingUser.getEmail().equals(user.getEmail())) {
                return null;
            }
        }
        user.setCreation_date(sqlDate);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(@NotNull User user, int id) {
        User userToUpdate = getUserById(id);

        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirst_name(user.getFirst_name());
        userToUpdate.setLast_name(user.getLast_name());
        userToUpdate.setUser_name(user.getUser_name());

        return userRepository.save(userToUpdate);
    }

    public User addFriend(int firstUser, int secondUser) {
        User firstFriend = getUserById(firstUser);
        User secondFriend = getUserById(secondUser);
        List<User> firstFriendList = firstFriend.getFriendlist();
        List<User> secondFriendList = secondFriend.getFriendlist();
        if (checkExistingFriends(firstUser, secondUser) != 0) {
            return null;
        } else {
            firstFriendList.add(secondFriend);
            secondFriendList.add(firstFriend);
            firstFriend.setFriendlist(firstFriendList);
            secondFriend.setFriendlist(secondFriendList);
            userRepository.save(secondFriend);
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

    public double applyFee(double transaction) {
        return transaction + (transaction * Fare.TRANSACTION_FARE);
    }

    public void updateBalanceTransaction(User sender, User receiver, double transaction) {
        double balanceTransmitter = sender.getBalance();
        sender.setBalance(balanceTransmitter - applyFee(transaction));

        double balanceReceiver = receiver.getBalance();
        receiver.setBalance(balanceReceiver + transaction);

        userRepository.save(sender);
        userRepository.save(receiver);
    }

    public User updateBalanceUser(int id, double amount, String type) {
        User userToUpdate = getUserById(id);
        if (Objects.equals(type, "Debit") && userToUpdate.getBalance() >= 0 && userToUpdate.getBalance() >= amount) {
            userToUpdate.setBalance(userToUpdate.getBalance() - amount);
            return userRepository.save(userToUpdate);
        } else if (Objects.equals(type, "Credit")) {
            userToUpdate.setBalance(userToUpdate.getBalance() + amount);
            return userRepository.save(userToUpdate);
        }
        return null;
    }
}
