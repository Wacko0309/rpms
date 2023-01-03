package com.inext.rpms;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@SpringBootTest
public class MailTest {
	
	@Autowired
	private JavaMailSender mailSender;

	@Test
	void sendToGmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo("vocaliod1015417@gmail.com");
		message.setSubject("検証コード");
		message.setText("検証コードは" );

		mailSender.send(message);
	}
}
