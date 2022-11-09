package com.PayMyBuddy.paymybuddy.service;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User addUser(User user) {
        List<User> userList = getUsers();
        for (User existingUser : userList){
            if (existingUser.getEmail().equals(user.getEmail())) {
                return null;
            }
        }
        return userRepository.save(user);
    }

    public User updateUser(User user, int id) {
        User userToUpdate = getUserById(id);

        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setFirst_name(user.getFirst_name());
        userToUpdate.setLast_name(user.getLast_name());
        userToUpdate.setUser_name(user.getUser_name());

        return userRepository.save(userToUpdate);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
