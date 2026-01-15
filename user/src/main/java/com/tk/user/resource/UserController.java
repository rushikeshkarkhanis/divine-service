package com.tk.user.resource;

import com.tk.user.entity.User;
import com.tk.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/get-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get-user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/get-user-by-name/{name}")
    public User getUserById(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @PutMapping("/update-user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully with id: " + id;
    }
}
