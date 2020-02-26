package io.welfareteam.api.resource.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.welfareteam.api.controller.MoodController;
import io.welfareteam.api.controller.UserController;
import io.welfareteam.api.entity.Mood;
import io.welfareteam.api.resource.MoodModel;

@Component
public class MoodModelAssembler extends RepresentationModelAssemblerSupport<Mood, MoodModel> {
	
	@Autowired
	private UserModelAssembler assembler;

	public MoodModelAssembler() {
		super(MoodController.class, MoodModel.class);
	}
	
	@Override
	public MoodModel toModel(Mood entity) {
		
		MoodModel model = new MoodModel();
		
		model.setId(entity.getId());
		model.setComment(entity.getComment());
		model.setDay(entity.getDay());
		model.setLevel(entity.getLevel());
		model.setUser(assembler.toModel(entity.getUser()));
		model.setUserId(entity.getUser().getId());
		
		model.add(linkTo(methodOn(MoodController.class).getMood(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(UserController.class).getUser(entity.getUser().getId())).withRel("user"));
		
		return model;
	}

}
