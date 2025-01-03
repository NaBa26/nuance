package com.ecommerce.nuance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Password Encoder Bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16); // Consistent strength
    }
    
    // Cookie Serializer for SameSite and Secure cookie
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setSameSite("None"); // Set SameSite to None
        cookieSerializer.setUseSecureCookie(true);  // Ensure cookie is sent over HTTPS
        return cookieSerializer;
    }

    // Security Filter Chain for Spring Security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HTTP security
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/api/process-login", "/api/process-signup", "/api/books", "/api/home", "/api/email/forgot-password", "/api/check-session", "/api/check-auth", "api/books/*", "api/log-out").permitAll()
                    .anyRequest().authenticated())  // Require authentication for other routes
            .oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/api/process-login")  // Custom login page
                .defaultSuccessUrl("/api/home", true));

        return http.build();
    }
}
