package io.welfareteam.api.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import io.welfareteam.api.entity.MailSetting;
import io.welfareteam.api.entity.Team;
import io.welfareteam.api.repository.MailSettingRepository;
import io.welfareteam.api.repository.TeamRepository;

@RunWith(SpringRunner.class)
public class TeamServiceTest {

	@Autowired
	private TeamService				teamService;

	@MockBean
	private TeamRepository			teamRepository;

	@MockBean
	private MailSettingRepository	mailSettingRepository;

	@TestConfiguration
	static class TeamServiceTestContextConfiguration {

		@Bean
		public TeamService teamService() {
			return new TeamService();
		}
	}

	@Test
	public void testGetTeamsToSendMail() {
		
		Calendar dateToSend = Calendar.getInstance();
		dateToSend.set(Calendar.HOUR_OF_DAY, 12);
		dateToSend.set(Calendar.MINUTE, 00);
		dateToSend.set(Calendar.SECOND, 0);
		dateToSend.set(Calendar.MILLISECOND, 0);

		MailSetting mailSetting = new MailSetting();
		mailSetting.setLanguage(Locale.FRENCH.getDisplayLanguage());
		mailSetting.setSendingTime(dateToSend.getTime());
		mailSetting.setMonday(true);
		mailSetting.setTuesday(true);
		mailSetting.setWednesday(true);
		mailSetting.setThursday(true);
		mailSetting.setFriday(true);
		mailSetting.setSaturday(true);
		mailSetting.setSunday(true);
		mailSettingRepository.save(mailSetting);

		Team team = new Team();
		team.setAdmins(null);
		team.setBoard(null);
		team.setMailSetting(mailSetting);
		team.setMembers(null);
		team.setName("teamTest");
		teamRepository.save(team);

		when(teamRepository.findBySettingMailSendingTimeBetween(Mockito.any(), Mockito.any())).thenAnswer(
		            new Answer<List<Team>>(){
		            @Override
		            public List<Team> answer(InvocationOnMock invocation){
		            	Date dateBefore = (Date) invocation.getArgument(0);
		            	Calendar calendarBefore = Calendar.getInstance();
		            	calendarBefore.setTime(dateBefore);
		            	Date dateAfter = (Date) invocation.getArgument(1);
		            	Calendar calendarAfter = Calendar.getInstance();
		            	calendarAfter.setTime(dateAfter);
		            	
		                if (dateToSend.after(calendarBefore) && dateToSend.before(calendarAfter)){
		                    return Arrays.asList(team);
		                }
		                return new ArrayList<Team>();
		            }});
		
		
		Calendar calendar = Calendar.getInstance();
		// 1st test
		calendar.setTime(dateToSend.getTime());
		calendar.add(Calendar.MINUTE, -10);
		List<Team> teams = teamService.getTeamsToSendMail(calendar.getTime());
		Assert.isTrue(CollectionUtils.isEmpty(teams), "1st test : Null or empty team list");

		// 2nd test
		calendar.setTime(dateToSend.getTime());
		calendar.add(Calendar.MINUTE, -5);
		teams = teamService.getTeamsToSendMail(calendar.getTime());
		Assert.isTrue(!CollectionUtils.isEmpty(teams), "2nd test : Null or empty team list");

		// 3rd test
		calendar.setTime(dateToSend.getTime());
		teams = teamService.getTeamsToSendMail(calendar.getTime());
		Assert.isTrue(CollectionUtils.isEmpty(teams), "3rd test : Null or empty team list");
		
		// 4th test
		calendar.setTime(dateToSend.getTime());
		calendar.add(Calendar.MINUTE, 1);
		teams = teamService.getTeamsToSendMail(calendar.getTime());
		Assert.isTrue(CollectionUtils.isEmpty(teams), "4th test : Null or empty team list");

	}
}
