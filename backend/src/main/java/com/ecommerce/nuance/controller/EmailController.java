package com.ecommerce.nuance.controller;

import com.ecommerce.nuance.model.Email;
import com.ecommerce.nuance.service.EmailService;
import com.ecommerce.nuance.service.EmailServiceImpl;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.aot.hint.annotation.ReflectiveRuntimeHintsRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<?> forgotPassword(@RequestBody Email details) {
		return emailService.sendforgotPasswordMail(details);

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
