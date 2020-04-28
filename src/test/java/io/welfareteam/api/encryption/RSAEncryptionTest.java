package io.welfareteam.api.encryption;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
public class RSAEncryptionTest {

	@Autowired
private RSAEncryption encyption;

	@TestConfiguration
	static class RSAEncryptionTestContextConfiguration {

		@Bean
		public RSAEncryption rsaEncryption() {
			return new RSAEncryption();
		}
	}

	@Test
	public void testEncryptAndDecrypt() {
		
		String key = "ufhzfhizoefnkSDC .C?<W:;,L/?FCLS?C564594984";
		String originalText = "12345678901234567890";
		String encryptMessage = RSAEncryption.encrypt(originalText, key);
		String decryptMessage = RSAEncryption.decrypt(encryptMessage, key);
		
		System.out.println("orignal message : " + originalText);
		System.out.println("crypted message : " + encryptMessage);
		System.out.println("decrypted message : " + decryptMessage);
		
		Assert.isTrue(decryptMessage.equals(originalText), "texts are different");
		Assert.isTrue(encryptMessage.length() == 44, "crypted messag is too long");
		
		originalText = "09876543210987654321";
		encryptMessage = RSAEncryption.encrypt(originalText, key);
		decryptMessage = RSAEncryption.decrypt(encryptMessage, key);
		
		System.out.println("orignal message : " + originalText);
		System.out.println("crypted message : " + encryptMessage);
		System.out.println("decrypted message : " + decryptMessage);
		
		Assert.isTrue(decryptMessage.equals(originalText), "texts are different");
		Assert.isTrue(encryptMessage.length() == 44, "crypted messag is too long");
		
		originalText = "abcdefghijklmnopqrst";
		encryptMessage = RSAEncryption.encrypt(originalText, key);
		decryptMessage = RSAEncryption.decrypt(encryptMessage, key);
		
		System.out.println("orignal message : " + originalText);
		System.out.println("crypted message : " + encryptMessage);
		System.out.println("decrypted message : " + decryptMessage);
		
		Assert.isTrue(decryptMessage.equals(originalText), "texts are different");
		Assert.isTrue(encryptMessage.length() == 44, "crypted messag is too long");
		
	}
}