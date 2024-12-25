package com.ecommerce.nuance.service;
import org.springframework.http.ResponseEntity;

import com.ecommerce.nuance.model.Email;

public interface EmailService {

	boolean userExists(String email);
	ResponseEntity<?> sendforgotPasswordMail(String email);
}
