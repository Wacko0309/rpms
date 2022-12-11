package com.inext.rpms;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.servlet.http.HttpSession;

@SpringBootTest
public class MailTest {
	
	@Autowired
	private JavaMailSender mailSender;

	@Test
	void sendToGmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		
		HttpSession httpSession = null;
		
		@SuppressWarnings("null")
		String verifyCode = httpSession.getAttribute("verifyCode").toString();
		
		message.setTo("vocaliod1015417@gmail.com");
		message.setSubject("���؃R�[�h");
		message.setText("���؃R�[�h��" + verifyCode);

		mailSender.send(message);
	}
}
