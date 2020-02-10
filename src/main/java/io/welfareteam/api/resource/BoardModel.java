package io.welfareteam.api.resource;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class BoardModel extends RepresentationModel<BoardModel> {
	
	private String name;
	
	private List<Long> teamIds;
	
	public BoardModel() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(List<Long> teamIds) {
		this.teamIds = teamIds;
	}

}
