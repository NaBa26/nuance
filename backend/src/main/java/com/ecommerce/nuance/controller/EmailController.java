package com.ecommerce.nuance.controller;

import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody User user) {
		if (user == null || user.getEmail() == null || user.getEmail().isBlank()) {
	        return ResponseEntity.badRequest().body("Email is required");
	    }
		if(emailService.userExists(user.getEmail()))
		{
			System.out.println("\n------------Log------------------");
			System.out.println("The user exists with email: "+user.getEmail());
			return emailService.sendforgotPasswordMail(user.getEmail());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}
	
	//This will be configured in the future with admin panel
//	@PostMapping("/advertisement")
//	public String advertisementEmail(@RequestBody Email details)
//	{
//		String statuString = emailService.sendSimpleMail(details);
//		return statuString;
//	}
	
	
	//This will be configured with new device login alert in the future
//	@PostMapping("/alert")
//	public String alertEmail(@RequestBody Email details)
//	{
//		String statusString = emailService.sendSimpleMail(details);
//		
//		return statusString;
//	}
}
