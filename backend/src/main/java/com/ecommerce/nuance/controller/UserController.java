package com.ecommerce.nuance.controller;

import com.ecommerce.nuance.dto.LoginResult;
import com.ecommerce.nuance.dto.SessionManagement;
import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	    public ResponseEntity<?> createUser(@RequestBody User user, HttpServletRequest request) {
	        try {
	            // Create the user in the database
	            User createdUser = this.userService.createUser(user);
	            
	            HttpSession session=request.getSession();

	            // Set the user details in the session
	            SessionManagement.setUser(session, createdUser);  // Store the entire user object

	            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }

	    @PostMapping("/process-login")
	    public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request) {
	        // Verify credentials first
	        LoginResult result = userService.verifyCredentials(user.getUsername(), user.getPassword());

	        switch (result.getMessage()) {
	            case "User not found":
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.getMessage());

	            case "Wrong password":
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.getMessage());

	            case "Success":
	                // Check if user is already logged in
	            	HttpSession session=request.getSession();
	                if (SessionManagement.isSessionValid(session)) {
	                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already logged in. Redirecting...");
	                }

	                Optional<User> authenticatedUser = result.getUser();
	                authenticatedUser.ifPresent(u -> {
	                    u.setPassword(null); // Exclude password for safety
	                    SessionManagement.setUser(session, u); // Store the user without sensitive info
	                    System.out.println("Active user is: "+SessionManagement.getUser(session) + " ,for session - "+ session);
	                    
	                });
	                return ResponseEntity.ok(authenticatedUser);

	            default:
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
	        }
	    }

	    @PostMapping("/log-out")
	    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response) {
	        // Log the cookies sent with the request
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                System.out.println("Cookie: " + cookie.getName() + " = " + cookie.getValue());
	            }
	        } else {
	            System.out.println("No cookies found in the request.");
	        }
	        
	        HttpSession session=request.getSession(false);

	        // Now check if the session is valid
	        if (!SessionManagement.isSessionValid(session)) {
	            System.out.println("No active session found. User is: " + SessionManagement.getUser(session) + " & session is - " + session);
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "No active session found."));
	        }

	        // If session is valid, invalidate it
	        System.out.println("Invalidating session for user: " + SessionManagement.getUser(session) + " ,for session - " + session);
	        SessionManagement.invalidateSession(session);   
	        
	        Cookie cookie = new Cookie("MY_SESSION_COOKIE", null);  // Use your session cookie name here
	        cookie.setMaxAge(0);  // Expire immediately
	        cookie.setPath("/");  // Set the path to match the cookie path
	        cookie.setDomain("localhost");  // Set the domain (if applicable)
	        cookie.setHttpOnly(true);
	        cookie.setSecure(true);  // Set this to true if using HTTPS

	        response.addCookie(cookie);  // Add the expired cookie to the response
	        System.out.println("Cleared session cookie.");


	        // Prepare and return the response
	        Map<String, String> finalResponse = new HashMap<>();
	        finalResponse.put("message", "Logged out successfully");
	        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "User logged out successfully"));
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
}
