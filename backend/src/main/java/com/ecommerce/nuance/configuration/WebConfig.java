package com.ecommerce.nuance.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply CORS to all paths
            .allowedOrigins("http://localhost:5173/")  // Allow specific origins
            .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific methods
            .allowedHeaders("Content-Type", "Authorization")  // Allow specific headers
            .allowCredentials(true);  // Allow credentials
    }
}
