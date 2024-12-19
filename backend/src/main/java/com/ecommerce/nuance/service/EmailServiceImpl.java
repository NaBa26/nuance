package com.ecommerce.nuance.service;

import com.ecommerce.nuance.model.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

 @Autowired private JavaMailSender javaMailSender;

 @Value("${spring.mail.username}") private String sender;
 public ResponseEntity<?> sendforgotPasswordMail(Email details)
 {

     // Try block to check for exceptions
     try {
         SimpleMailMessage mailMessage
             = new SimpleMailMessage();

         mailMessage.setFrom(sender);
         mailMessage.setTo(details.getRecipient());
         mailMessage.setText(details.getBody());
         mailMessage.setSubject(details.getSubject());

         javaMailSender.send(mailMessage);
         return new ResponseEntity<>("Email sent", HttpStatus.CREATED);    }

     catch (Exception e) {
    	 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
     }
 }
 
}
