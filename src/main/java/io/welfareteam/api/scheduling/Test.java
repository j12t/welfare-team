package io.welfareteam.api.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.welfareteam.api.entity.Team;
import io.welfareteam.api.entity.User;
import io.welfareteam.api.service.EmailService;
import io.welfareteam.api.service.TeamService;

@Component
public class Test {

	@Autowired
	private EmailService					emailService;

	@Autowired
	private TeamService						teamService;

	private static final Logger				log			= LoggerFactory.getLogger(Test.class);

	private static final SimpleDateFormat	dateFormat	= new SimpleDateFormat("HH:mm:ss");

	@Scheduled(cron = "0 0/" + TeamService.sendingMailIntervalInMinutes + " * * * *")
//	@Scheduled(cron = "0 * * * * *")
	public void reportCurrentTime() {

		Date date = new Date();
		
		log.info("The time is now {}", dateFormat.format(date));
		
		List<Team> teams = teamService.getTeamsToSendMail(date);
		
		for (Team team : teams) {
			for (User member : team.getMembers()) {
				emailService.sendSimpleMessage("thibault.jerome@gmail.com", "Mail for User", "member mail : " + member.getName() + " " + member.getFirstname());
			}
			
			for (User member : team.getAdmins()) {
				emailService.sendSimpleMessage("thibault.jerome@gmail.com", "Mail for Admin", "member mail : " + member.getName() + " " + member.getFirstname());
			}
		}
	}
}