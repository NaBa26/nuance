package com.ecommerce.nuance.dto;

import java.util.Optional;

import com.ecommerce.nuance.model.User;

public class LoginResult {
    private Optional<User> user;
    private String message;

    public LoginResult(Optional<User> user, String message) {
        this.user = user;
        this.message = message;
    }

    public Optional<User> getUser() {
        return user;
    }

    public void setUser(Optional<User> user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
