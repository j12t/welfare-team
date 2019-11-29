package io.welfareteam.api.resource;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class BoardModel extends RepresentationModel<BoardModel> {
	
	private String name;
	
	public BoardModel() {
		super();
	}

	private List<TeamModel> teams;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TeamModel> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamModel> teams) {
		this.teams = teams;
	}

}
