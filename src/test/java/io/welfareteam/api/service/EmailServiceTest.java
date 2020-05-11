package io.welfareteam.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-local.properties")
public class EmailServiceTest {

	@Autowired
	private EmailService emailService;

	@TestConfiguration
	static class EmailServiceTestContextConfiguration {

		@Bean
		public EmailService emailService() {
			return new EmailService();
		}
	}

	@Test
	public void testSendSimpleMail() {

		String text = "This is a simple mail";

		// sending mail to the account defined to send mail
		JavaMailSenderImpl sender = (JavaMailSenderImpl) emailService.getJavaMailSender();
		emailService.sendSimpleMessage(sender.getUsername(), "Test Email", text);
	}

}
