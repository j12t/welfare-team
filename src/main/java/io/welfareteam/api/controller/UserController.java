package io.welfareteam.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.welfareteam.api.entity.User;
import io.welfareteam.api.repository.UserRepository;

@RestController
@RequestMapping("/v1/users/")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<User> getAllUsers(Pageable page) {
		
		return repository.findAll(page);
	}
}
