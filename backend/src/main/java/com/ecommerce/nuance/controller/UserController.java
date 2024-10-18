package com.ecommerce.nuance.controller;

import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // Constructor injection (if needed)
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/process-signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User createdUser = this.userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT request to update user info
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable long userId, @RequestBody User updatedUser) {
        try {
            User updated = this.userService.updateUser(userId, updatedUser);
            return new ResponseEntity<>(updated, HttpStatus.OK); // HTTP 200 for success
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // HTTP 404 for not found
        }
    }

    // DELETE request to remove a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        try {
            this.userService.deleteUser(Long.parseLong(userId));
            return new ResponseEntity<>(HttpStatus.OK); // HTTP 200 for success
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500 for errors
        }
    }

    // Handle GET requests if needed
//    @GetMapping("/{userId}")
//    public ResponseEntity<?> getUserById(@PathVariable long userId) {
//        Optional<User> user = this.userService.findUserById(userId);
//        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
}
