package com.ecommerce.nuance.service;

import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.repository.UserRepository;

import jakarta.mail.internet.MimeMessage;

import java.security.SecureRandom;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(UserService userService, UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

 @Value("${spring.mail.username}") private String sender;
 public ResponseEntity<?> sendforgotPasswordMail(String recipient) {
	    try {
	        String tempPassword = generateTemporaryPassword();

	        userService.updatePassword(recipient, tempPassword);

	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

	        messageHelper.setFrom(sender);
	        messageHelper.setTo(recipient);
	        messageHelper.setSubject("Password Reset");
	        System.out.println("\n------------Log------------------");
			System.out.println("New password is: "+tempPassword);
			System.out.println("Sender: "+sender);

	        String htmlBody = "<html>" +
	                "<body style='font-family: Arial, sans-serif; padding: 20px;'>" +
	                "<h2 style='color: #333;'>Forgot Password Request</h2>" +
	                "<p>Hello,</p>" +
	                "<p>We received a request to reset your password. Your new temporary password is below:</p>" +
	                "<h3 style='color: #4CAF50;'>Temporary Password: " + tempPassword + "</h3>" +
	                "<p>Please log in with this password and immediately change it to something secure in your account settings.</p>" +
	                "<p>If you didn't request this, please ignore this email.</p>" +
	                "<p>Thanks,<br>The Support Team</p>" +
	                "</body>" +
	                "</html>";

	        messageHelper.setText(htmlBody, true);
	        javaMailSender.send(mimeMessage);

	        return new ResponseEntity<>("Email sent", HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}


 private String generateTemporaryPassword() {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
	    SecureRandom random = new SecureRandom();
	    StringBuilder password = new StringBuilder(10);

	    for (int i = 0; i < 10; i++) {
	        int index = random.nextInt(characters.length());
	        password.append(characters.charAt(index));
	    }

	    return password.toString();
	}



public boolean userExists(String email)
 {
	 Optional<User> userOptional=userRepository.findByEmail(email);
	 if(userOptional.isPresent())
	 {
		 return true;
	 }
	 return false;
 }
 
}
