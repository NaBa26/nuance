package com.ecommerce.nuance;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("com.ecommerce.nuance.model") //To map with the model package
@EnableJpaRepositories("com.ecommerce.nuance.repository")
@EnableTransactionManagement
public class NuanceApplication {
	public static void main(String[] args) {
	SpringApplication.run(NuanceApplication.class, args);
	}

}
