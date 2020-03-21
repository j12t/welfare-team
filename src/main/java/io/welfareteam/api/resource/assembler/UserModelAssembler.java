package io.welfareteam.api.resource.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.welfareteam.api.controller.UserController;
import io.welfareteam.api.entity.Team;
import io.welfareteam.api.entity.User;
import io.welfareteam.api.resource.TeamModel;
import io.welfareteam.api.resource.UserModel;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {
	
	@Autowired
	private TeamModelAssembler assembler;

	public UserModelAssembler() {
		super(UserController.class, UserModel.class);
	}
	
	@Override
	public UserModel toModel(User entity) {
		
		UserModel model = new UserModel();
		model.setId(entity.getId());
		model.setEmail(entity.getEmail());
		model.setFirstname(entity.getFirstname());
		model.setName(entity.getName());
		
//		List<TeamModel> teamModels  = new ArrayList<TeamModel>();
//		for (Team team : entity.getTeams()) {
//			teamModels.add(assembler.toModel(team));
//		}
//		model.setTeams(teamModels);
		
		model.add(linkTo(methodOn(UserController.class).getUser(entity.getId())).withSelfRel());
		
		return model;
	}

}
