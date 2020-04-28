package io.welfareteam.api.controller;

import static io.welfareteam.api.config.SecurityUtils.getLogguedUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import io.welfareteam.api.entity.Mood;
import io.welfareteam.api.entity.User;
import io.welfareteam.api.repository.MoodRepository;
import io.welfareteam.api.repository.UserRepository;
import io.welfareteam.api.resource.MoodModel;
import io.welfareteam.api.resource.UserModel;
import io.welfareteam.api.resource.assembler.MoodModelAssembler;
import io.welfareteam.api.resource.assembler.UserModelAssembler;

@RestController
@CrossOrigin
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
	private MoodModelAssembler	moodAssembler;

	@Autowired
	private UserModelAssembler	userAssembler;

	@Autowired
	private MoodRepository		moodRepository;

	@Autowired
	private UserRepository		userRepository;

	@GetMapping
	public PagedModel<UserModel> getAllUsers(Pageable page) {

		Page<User> users = userRepository.findAll(page);

		PagedResourcesAssembler<User> pageAssembler = new PagedResourcesAssembler<>(null, null);

		return pageAssembler.toModel(users, userAssembler);
	}

	@GetMapping(path = "/{id}")
	public UserModel getUser(@PathVariable("id") Long id) {

		User user = userRepository.findById(id).get();
		return userAssembler.toModel(user);
	}

	@GetMapping(path = "/me")
	public UserModel getMe() {

		User user = getLogguedUser();
		return userAssembler.toModel(user);
	}

	@GetMapping(path = "/{id}/moods")
	public PagedModel<MoodModel> getUserMoods(@PathVariable("id") Long id, Pageable page) {

		if (id != getLogguedUser().getId()) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}

		Page<Mood> moods = moodRepository.findByUserId(page, id);

		PagedResourcesAssembler<Mood> pageAssembler = new PagedResourcesAssembler<>(null, null);

		return pageAssembler.toModel(moods, moodAssembler);
	}
	
	@PostMapping(path = "/{userId}/moods/{moodId}")
	public PagedModel<MoodModel> getUserMood(@PathVariable("userId") Long userId, @PathVariable("moodId") Long moodId, Pageable page) {

		if (userId != getLogguedUser().getId()) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}

		Page<Mood> moods = moodRepository.findByUserId(page, userId);

		PagedResourcesAssembler<Mood> pageAssembler = new PagedResourcesAssembler<>(null, null);

		return pageAssembler.toModel(moods, moodAssembler);
	}
	

}
