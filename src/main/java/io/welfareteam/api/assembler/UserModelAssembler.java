package io.welfareteam.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.welfareteam.api.controller.UserController;
import io.welfareteam.api.entity.User;
import io.welfareteam.api.resource.UserModel;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

	public UserModelAssembler() {
		super(UserController.class, UserModel.class);
	}
	
	@Override
	public UserModel toModel(User entity) {
		UserModel model = new UserModel();
		model.setEmail(entity.getEmail());
		model.setFirstname(entity.getFirstname());
		model.setName(entity.getName());
//		model.setTeams(entity.getTeams());
		
		model.add(linkTo(getControllerClass()).slash(entity.getId()).withSelfRel());
//		model.add(new Link("/payments/{orderId}").withRel(LinkRelation.of("payments")) //
//	            .expand(model.getContent().getOrderId())
		
		return null;
	}

}
