package com.ecommerce.nuance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/*The bean annotation is mostly used when I want
	to manually instantiate the bean object, in general when i am
	Injecting the entity object inside the controller class, it's also 
	a bean, because i am not hard coding the object */
    @Bean																		
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	//this is to specify that which end points would be accessible to all
        http.authorizeHttpRequests(authorizeHttpRequests ->authorizeHttpRequests
        		.requestMatchers("/", "/login", "/**")
        		.permitAll().anyRequest()
        		.authenticated())
        //for o-auth
            .oauth2Login(oauth2Login ->
                oauth2Login
                    .loginPage("/login")
                    .defaultSuccessUrl("/home", true)
            );
        return http.build();
    }
}
