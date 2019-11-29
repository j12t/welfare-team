package io.welfareteam.api.resource;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class UserModel extends RepresentationModel<UserModel> {
	
	private String email;
	
	private String name;
	
	private String firstname;
	
	private List<TeamModel> teams;

	public UserModel() {
		super();
	}

	public UserModel(Link initialLink) {
		super(initialLink);
	}

	public UserModel(List<Link> initialLinks) {
		super(initialLinks);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public List<TeamModel> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamModel> teams) {
		this.teams = teams;
	}
	
}
