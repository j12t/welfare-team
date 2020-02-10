package io.welfareteam.api.resource;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class TeamModel extends RepresentationModel<TeamModel> {

	private String name;

	private List<Long> admins;

	private List<Long> members;
	
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
	
	
	
}
