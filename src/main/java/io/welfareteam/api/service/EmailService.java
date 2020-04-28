package io.welfareteam.api.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	 @Value("${mail.host}")
	 private String host;
	
	 @Value("${mail.port}")
	 private int port;
	
	 @Value("${mail.username}")
	 private String username;
	
	 @Value("${mail.password}")
	 private String password;
	
	 @Value("${mail.smtp.auth}")
	 private boolean smtpAuth;
	
	 @Value("${mail.smtp.starttls.enable}")
	 private boolean tlsEnabled;
	
	 @Value("${mail.smtp.ssl.trust}")
	 private String sslTrust;
	
	public void sendSimpleMessage(String to, String subject, String text) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		getJavaMailSender().send(message);
	}
	
	public JavaMailSender getJavaMailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);

		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", smtpAuth);
		props.put("mail.smtp.starttls.enable", tlsEnabled);
		props.put("mail.debug", true);
		props.put("mail.smtp.ssl.trust", sslTrust);

		return mailSender;
	}


}