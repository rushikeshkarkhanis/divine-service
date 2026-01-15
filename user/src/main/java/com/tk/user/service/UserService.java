package com.tk.user.service;

import com.tk.user.entity.User;
import com.tk.user.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setFName(userDetails.getFName());
        user.setLName(userDetails.getLName());
        user.setAge(userDetails.getAge());
        user.setAddresses(userDetails.getAddresses());
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User getUserByName(String fname) {
        return userRepo.findUserByfName(fname).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
