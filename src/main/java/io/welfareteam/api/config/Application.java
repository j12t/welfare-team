package io.welfareteam.api.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.welfareteam.api.entity.User;
import io.welfareteam.api.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "io.welfareteam.api.repository")
@ComponentScan(basePackages = { "io.welfareteam.api.controller", "io.welfareteam.api.assembler" })
@EntityScan(basePackages = "io.welfareteam.api.entity")
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {

			User user = new User();
			user.setEmail("jerome.thibault@natixis.com");
			user.setFirstname("Jerome");
			user.setName("Thibault");

			repository.save(user);

			List<User> users = repository.findAll();

			if (users != null && !users.isEmpty()) {

				log.info("Users found : " + users.size());

				for (User item : repository.findAll()) {
					log.info(item.toString());
				}
			}

		};
	}

}
