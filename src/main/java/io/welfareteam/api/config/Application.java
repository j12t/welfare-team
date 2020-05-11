package io.welfareteam.api.config;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.welfareteam.api.common.MoodLevel;
import io.welfareteam.api.entity.MailSetting;
import io.welfareteam.api.entity.Mood;
import io.welfareteam.api.entity.Team;
import io.welfareteam.api.entity.User;
import io.welfareteam.api.repository.MailSettingRepository;
import io.welfareteam.api.repository.MoodRepository;
import io.welfareteam.api.repository.TeamRepository;
import io.welfareteam.api.repository.UserRepository;
import io.welfareteam.api.service.TeamService;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "io.welfareteam.api.repository")
@ComponentScan(basePackages = { "io.welfareteam.api.authentication", "io.welfareteam.api.config", "io.welfareteam.api.controller", "io.welfareteam.api.resource.assembler",
		"io.welfareteam.api.scheduling", "io.welfareteam.api.service" })
@EntityScan(basePackages = "io.welfareteam.api.entity")
@EnableScheduling
@EnableTransactionManagement
public class Application {

	//J12T check if @enablescheduling is mandatory here
	// J12T check if @EnableTransactionManagement is mandatory here
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Transactional(value = TxType.REQUIRED)
	public CommandLineRunner demo(UserRepository userRepository, MoodRepository moodRepository, 
			MailSettingRepository mailSettingRepository, TeamRepository teamRepository, TeamService teamService) {

		return (args) -> {

			// create user user
			User user = new User();
			user.setLogin("user");
			user.setPassword(new BCryptPasswordEncoder().encode("password"));
			user.setEmail("user@github.com");
			user.setFirstname("User");
			user.setName("User");
			user.setTeams(null);
			user.setRoles(Arrays.asList("USER"));

			userRepository.save(user);

			// create user admin
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

			// create a mood for user admin
			Mood mood = new Mood();
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
			
			// create a team for user admin
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.add(Calendar.MINUTE, 10);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			MailSetting mailSetting = new MailSetting();
			mailSetting.setLanguage(Locale.FRENCH.getDisplayLanguage());
			mailSetting.setSendingTime(calendar.getTime());
			mailSetting.setMonday(true);
			mailSetting.setTuesday(true);
			mailSetting.setWednesday(true);
			mailSetting.setThursday(true);
			mailSetting.setFriday(true);
			mailSetting.setSaturday(true);
			mailSetting.setSunday(true);

			mailSettingRepository.save(mailSetting);
			
			Team team = new Team();
			team.setAdmins(Arrays.asList(user));
			team.setBoard(null);
			team.setMailSetting(mailSetting);
			team.setMembers(Arrays.asList(user));
			team.setName("teamAdmin");
			
			teamRepository.save(team);

			List<Team> teams = teamService.getAllTeams();

			if (teams != null && !teams.isEmpty()) {

				log.info("Teams found : " + teams.size());

				for (Team item : teams) {
					log.info(item.toString());
				}
			}
		};
	}
}
