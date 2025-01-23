package com.ecommerce.nuance.controller;

import com.ecommerce.nuance.dto.LoginResult;
import com.ecommerce.nuance.dto.SessionManagement;
import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.service.AuthenticationService;
import com.ecommerce.nuance.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/process-signup")
    public ResponseEntity<?> createUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            // Create the user in the database
            User createdUser = userService.createUser(user);

            // Set session and authenticate
            HttpSession session = request.getSession();
            SessionManagement.setUser(session, createdUser);

            // Authenticate user dynamically and save context
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
            authenticationService.authenticateUser(createdUser.getUsername(), authorities, request, response);

            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/process-login")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        LoginResult result = userService.verifyCredentials(user.getUsername(), user.getPassword());

        switch (result.getMessage()) {
            case "User not found":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

            case "Wrong password":
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");

            case "Success":
                HttpSession session = request.getSession();

                if (SessionManagement.isSessionValid(session)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already logged in");
                }

                result.getUser().ifPresent(authenticatedUser -> {
                    SessionManagement.setUser(session, authenticatedUser);

                    // Authenticate user dynamically and save context
                    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
                    authenticationService.authenticateUser(authenticatedUser.getUsername(), authorities, request, response);
                });

                return ResponseEntity.ok(result.getUser().orElse(null));

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @GetMapping("/verify-session")
    public ResponseEntity<?> isSessionActive(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); //false is used here so that this won't create a session on it's own
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && SessionManagement.isSessionValid(session)) {
            return ResponseEntity.ok("Session is active");
        }
        
        SecurityContextHolder.clearContext(); 
        Cookie sessionCookie = new Cookie("MY_SESSION_COOKIE", null);
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        response.addCookie(sessionCookie);
        //when the front end will run this function on regular intervals and would find null in the session object
        //then this will clear the context in the context holder and would clear the cookies automatically as well
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
    }

    @PostMapping("/log-out")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        if (session == null || !SessionManagement.isSessionValid(session)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No active session found");
        }

        // Invalidate session and clear context
        SessionManagement.invalidateSession(session);
        SecurityContextHolder.clearContext();

        // Expire session cookie
        Cookie sessionCookie = new Cookie("MY_SESSION_COOKIE", null);
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        response.addCookie(sessionCookie);

        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/check-auth")
    public ResponseEntity<?> checkAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            Map<String, Object> response = new HashMap<>();
            response.put("authenticated", true);
            response.put("user", authentication.getName());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<?> updateUser(@RequestParam("id") long userId, @RequestBody User updatedUser, HttpServletRequest request, HttpServletResponse response) {
        try {
            User updated = userService.updateUser(userId, updatedUser);

            // Re-authenticate user after profile update (optional)
            HttpSession session = request.getSession();
            SessionManagement.setUser(session, updated);
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
            authenticationService.authenticateUser(updated.getUsername(), authorities, request, response);

            return ResponseEntity.ok(updated);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
