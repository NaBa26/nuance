package com.ecommerce.nuance.service;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final SecurityContextRepository securityContextRepository;

    public AuthenticationService(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }

    public void authenticateUser(String username, List<SimpleGrantedAuthority> authorities, HttpServletRequest request, HttpServletResponse response) {
        // Create a new SecurityContext
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        // Set authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            username,
            null,
            authorities
        );
        context.setAuthentication(authentication);

        // Update the SecurityContextHolder
        SecurityContextHolder.setContext(context);

        // Save the updated context to the repository
        securityContextRepository.saveContext(context, request, response);
    }

    public Authentication checkAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
