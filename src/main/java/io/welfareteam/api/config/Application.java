package io.welfareteam.api.config;

import java.util.Arrays;
import java.util.Date;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.welfareteam.api.common.MoodLevel;
import io.welfareteam.api.entity.Mood;
import io.welfareteam.api.entity.User;
import io.welfareteam.api.repository.MoodRepository;
import io.welfareteam.api.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "io.welfareteam.api.repository")
@ComponentScan(basePackages = { "io.welfareteam.api.authentication", "io.welfareteam.api.config", "io.welfareteam.api.controller", "io.welfareteam.api.resource.assembler" })
@EntityScan(basePackages = "io.welfareteam.api.entity")
@EnableScheduling
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, MoodRepository moodRepository) {
		return (args) -> {

			User user = new User();
			user.setLogin("user");
			user.setPassword(new BCryptPasswordEncoder().encode("password"));
			user.setEmail("user@github.com");
			user.setFirstname("User");
			user.setName("User");
			user.setTeams(null);
			user.setRoles(Arrays.asList("USER"));

			userRepository.save(user);
			
			
			user = new User();
			user.setLogin("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("password"));
			user.setEmail("admin@github.com");
			user.setFirstname("Admin");
			user.setName("Admin");
			user.setTeams(null);
			user.setRoles(Arrays.asList("ADMIN"));

			userRepository.save(user);

			List<User> users = userRepository.findAll();

			if (users != null && !users.isEmpty()) {

				log.info("Users found : " + users.size());

				for (User item : users) {
					log.info(item.toString());
				}
			}
			
			Mood mood  = new Mood();
			mood.setComment("blabla");
			mood.setDay(new Date());
			mood.setLevel(MoodLevel.GOOD);
			mood.setUser(user);
			
			moodRepository.save(mood);
			List<Mood> moods = moodRepository.findAll();

			if (moods != null && !moods.isEmpty()) {

				log.info("Moods found : " + moods.size());

				for (Mood item : moods) {
					log.info(item.toString());
				}
			}
			
			
		};
	}

}
