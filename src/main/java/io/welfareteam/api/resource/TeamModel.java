package io.welfareteam.api.resource;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class TeamModel extends RepresentationModel<TeamModel> {

	private Long				id;

	private String				name;

	private List<Long>			admins;

	private List<Long>			members;

	// private BoardModel board;

	private MailSettingModel	mailSetting;

	public TeamModel() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Long> admins) {
		this.admins = admins;
	}

	public List<Long> getMembers() {
		return members;
	}

	public void setMembers(List<Long> members) {
		this.members = members;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MailSettingModel getMailSetting() {
		return mailSetting;
	}

	public void setMailSetting(MailSettingModel mailSetting) {
		this.mailSetting = mailSetting;
	}

}
