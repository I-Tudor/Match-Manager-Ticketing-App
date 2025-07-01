package org.example.service;

import org.example.domain.User;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository = new UserRepository();

    public void registerUser(String username, String password) {
        if (userRepository.getById(username) != null) {
            logger.warn("User with username {} already exists.", username);
            return;
        }
        User user = new User(username, password);
        userRepository.add(user);
        logger.info("User {} registered successfully.", username);
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.getById(username);
        if (user != null && user.getPassword().equals(password)) {
            logger.info("User {} authenticated successfully.", username);
            return true;
        }
        logger.warn("Authentication failed for user {}.", username);
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public void updateUserPassword(String username, String newPassword) {
        User user = userRepository.getById(username);
        if (user != null) {
            user.setPassword(newPassword);
            userRepository.update(username, user);
            logger.info("Password updated for user {}.", username);
        } else {
            logger.warn("User {} not found.", username);
        }
    }

    public void deleteUser(String username) {
        if (userRepository.getById(username) != null) {
            userRepository.delete(username);
            logger.info("User {} deleted successfully.", username);
        } else {
            logger.warn("User {} not found.", username);
        }
    }
}
