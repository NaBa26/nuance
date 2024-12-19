package com.ecommerce.nuance.service;
import org.springframework.http.ResponseEntity;

import com.ecommerce.nuance.model.Email;

public interface EmailService {

	ResponseEntity<?> sendforgotPasswordMail(Email details);
}
