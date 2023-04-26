package com.mhj.base.util;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailManager {
	
	@Value("${spring.mail.username}")
	private String sender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(String to, String subject, String text) throws Exception {
		//HTML 태그도 전송
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setFrom(sender);
		message.addRecipient(RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
		
		//HTML 태그를 무시하고 그대로 텍스트 전송 (간단)
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(sender);
//		message.setTo(to);
//		message.setSubject(subject);
//		message.setText(text);
//		javaMailSender.send(message);
	}

}
