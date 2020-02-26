package io.welfareteam.api.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.welfareteam.api.entity.Mood;
import io.welfareteam.api.entity.User;
import io.welfareteam.api.repository.MoodRepository;
import io.welfareteam.api.repository.UserRepository;
import io.welfareteam.api.resource.MoodModel;
import io.welfareteam.api.resource.assembler.MoodModelAssembler;

@RestController
@CrossOrigin
@RequestMapping("/v1/moods")
public class MoodController {

	@Autowired
	private MoodModelAssembler assembler;
	
	@Autowired
	private MoodRepository moodRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public PagedModel<MoodModel> getAllMoods(Pageable page) {
		
		Page<Mood> moods = moodRepository.findAll(page);
		
		PagedResourcesAssembler<Mood> pageAssembler =  new PagedResourcesAssembler<>(null, null);
		
		return pageAssembler.toModel(moods, assembler);
	}
	
	
	@RequestMapping(path= "/{id}", method = RequestMethod.GET)
	public MoodModel getMood(@PathVariable("id") Long id) {
		
		Mood mood = moodRepository.findById(id).get();
		return assembler.toModel(mood);
	}

	@RequestMapping(method = RequestMethod.POST)
	public MoodModel createMood(@RequestBody MoodModel moodModel) {
		
		Mood mood = new Mood();
		mood.setComment(moodModel.getComment());
		mood.setDay(moodModel.getDay());
		mood.setLevel(moodModel.getLevel());

		try {
			User user = userRepository.findById(moodModel.getUserId()).get();
			mood.setUser(user);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("User not found");
		}
		moodRepository.save(mood);
		return assembler.toModel(mood);
	}

}
