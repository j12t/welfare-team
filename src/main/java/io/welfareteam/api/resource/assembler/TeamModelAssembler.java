package io.welfareteam.api.resource.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.welfareteam.api.controller.TeamController;
import io.welfareteam.api.entity.Team;
import io.welfareteam.api.resource.TeamModel;

@Component
public class TeamModelAssembler extends RepresentationModelAssemblerSupport<Team, TeamModel> {

	public TeamModelAssembler() {
		super(TeamController.class, TeamModel.class);
	}
	
	@Override
	public TeamModel toModel(Team entity) {
		TeamModel model = new TeamModel();
		model.setName(entity.getName());
		
		model.add(linkTo(methodOn(TeamController.class).getTeam(entity.getId())).withSelfRel());
		
		return model;
	}

}
