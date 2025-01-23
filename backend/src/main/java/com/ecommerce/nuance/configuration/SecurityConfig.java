package com.ecommerce.nuance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Adjusted strength for password hashing
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setSameSite("None");  // Ensure SameSite is set for cross-domain requests
        cookieSerializer.setUseSecureCookie(true);  // Ensure cookies are secure (HTTPS)
        return cookieSerializer;
    }
    
    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.securityContext(securityContext -> securityContext
                .securityContextRepository(securityContextRepository())  // Use the defined bean
            )
            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs (you can enable it if needed for certain endpoints)
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers(
                        "/api/process-login",
                        "/api/process-signup",
                        "/api/books",
                        "/api/home",
                        "/api/email/forgot-password",
                        "/api/check-auth",
                        "/api/books/*",
                        "/api/verify-session"
                    ).permitAll()  // Permit unauthenticated access to certain endpoints
                    .anyRequest().authenticated()  // Other endpoints require authentication
            )
            // OAuth2 Login configuration
            .oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/api/process-login") // Custom login page
                .defaultSuccessUrl("/api/home", true)  // Redirect after successful login
            );

        return http.build();
    }
}
