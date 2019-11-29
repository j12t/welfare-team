package io.welfareteam.api.resource;

import org.springframework.hateoas.RepresentationModel;

public class TeamModel extends RepresentationModel<TeamModel> {

	private String name;

	public TeamModel() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
