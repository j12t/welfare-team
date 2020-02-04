package io.welfareteam.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.welfareteam.api.entity.User;
import io.welfareteam.api.repository.UserRepository;
import io.welfareteam.api.resource.UserModel;
import io.welfareteam.api.resource.assembler.UserModelAssembler;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
	private UserModelAssembler assembler;
	
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(method = RequestMethod.GET)
	public PagedModel<UserModel> getAllUsers(Pageable page) {
		
		Page<User> users = repository.findAll(page);
		
		PagedResourcesAssembler<User> pageAssembler =  new PagedResourcesAssembler<>(null, null);
		
		return pageAssembler.toModel(users, assembler);
	}
	
	
	@RequestMapping(path= "/{id}", method = RequestMethod.GET)
	public UserModel getUser(@PathVariable("id") Long id) {
		
		User user = repository.findById(id).get();
		return assembler.toModel(user);
	}

}
