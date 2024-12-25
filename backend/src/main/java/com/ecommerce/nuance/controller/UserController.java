package com.ecommerce.nuance.controller;

import com.ecommerce.nuance.dto.LoginResult;
import com.ecommerce.nuance.dto.SessionManagement;
import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.service.UserService;

import jakarta.servlet.http.HttpSession;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

	    private final UserService userService;

	    public UserController(UserService userService) {
	        this.userService = userService;
	    }
   

    /* because axios.post sends data in JSON format, so we would
     * need to use @requestbody, not @modelattribute
     */
    @PostMapping("/process-signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User createdUser = this.userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/process-login")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpSession session) {
        // Check if the user is already logged in
        if (SessionManagement.isSessionValid(session)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is already logged in.");
        }

        // Verify credentials
        LoginResult result = userService.verifyCredentials(user.getUsername(), user.getPassword());

        switch (result.getMessage()) {
            case "User not found":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.getMessage());

            case "Wrong password":
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.getMessage());

            case "Success":
                Optional<User> authenticatedUser = result.getUser();
                authenticatedUser.ifPresent(u -> u.setPassword(null)); // Exclude password for safety
                SessionManagement.setUsername(session, authenticatedUser.get().getUsername());
                return ResponseEntity.ok(authenticatedUser);

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }

    
    @GetMapping("/check-session")
    public ResponseEntity<Map<String, Boolean>> checkSession(HttpSession session) {
        boolean isActive = SessionManagement.isSessionValid(session);
        Map<String, Boolean> response = new HashMap<>();
        response.put("active", isActive);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        if (!SessionManagement.isSessionValid(session)) {
        	System.out.println("No active session found");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "No active session found."));
        }

        SessionManagement.invalidateSession(session);   
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logged out successfully");
        System.out.println("Logged out successfully");
        return ResponseEntity.ok(response);
    }



    
    @GetMapping("/check-auth")
    public Map<String, Object> checkAuth() {
        Map<String, Object> response = new HashMap<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                                  !"anonymousUser".equals(authentication.getPrincipal());

        response.put("authenticated", isAuthenticated);

        if (isAuthenticated) {
            response.put("user", authentication.getName());
        }

        return response;
    }

    // PUT request to update user info
    @PutMapping("/edit-profile/{userId}")
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
