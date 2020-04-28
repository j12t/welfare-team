package io.welfareteam.api.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.welfareteam.api.entity.Team;
import io.welfareteam.api.repository.TeamRepository;

@Service
@Transactional
public class TeamService {

	private static final Logger	log								= LoggerFactory.getLogger(TeamService.class);

	public static final int		sendingMailIntervalInMinutes	= 5;

	@Autowired
	private TeamRepository		teamRepository;

	// TODO J12T : delete this method
	public List<Team> getAllTeams() {
		List<Team> teams = teamRepository.findAll();

		if (teams != null && !teams.isEmpty()) {

			log.info("Teams found : " + teams.size());

			for (Team item : teams) {
				Hibernate.initialize(item.getAdmins());
				Hibernate.initialize(item.getMembers());
			}
		}

		return teams;
	}

	public List<Team> getTeamsToSendMail(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("parameter date can't be null");
		}

		// convert date into calendar
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);

		// isolate hour and minute of the date to find mailSetting corresponding
		Calendar calendarBefore = Calendar.getInstance();
		calendarBefore.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		calendarBefore.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		
		Calendar calendarAfter = Calendar.getInstance();
		calendarAfter.setTime(calendarBefore.getTime());
		calendarAfter.add(Calendar.MINUTE, sendingMailIntervalInMinutes);

		List<Team> teams = teamRepository.findBySettingMailSendingTimeBetween(calendarBefore.getTime(), calendarAfter.getTime());
		List<Team> finalTeams = new ArrayList<Team>();

		if (teams != null && !teams.isEmpty()) {

			log.info("Teams where sending mail time is setting to now : " + teams.size());
			
			// check if team must receive mail for the day
			for (Team item : teams) {
				
				boolean mustSend = false;
				
				switch (day) {
				case Calendar.MONDAY:
					mustSend = item.getMailSetting().isMonday();
					break;
				case Calendar.TUESDAY:
					mustSend = item.getMailSetting().isTuesday();
					break;
				case Calendar.WEDNESDAY:
					mustSend = item.getMailSetting().isWednesday();
					break;
				case Calendar.THURSDAY:
					mustSend = item.getMailSetting().isThursday();
					break;
				case Calendar.FRIDAY:
					mustSend = item.getMailSetting().isFriday();
					break;
				case Calendar.SATURDAY:
					mustSend = item.getMailSetting().isSaturday();
					break;
				case Calendar.SUNDAY:
					mustSend = item.getMailSetting().isSunday();
					break;
				}
				
				if (mustSend) {
					Hibernate.initialize(item.getAdmins());
					Hibernate.initialize(item.getMembers());
					finalTeams.add(item);
				}
			}
		}

		return finalTeams;

	}
}
