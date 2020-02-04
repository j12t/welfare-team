package io.welfareteam.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.welfareteam.api.entity.Team;
import io.welfareteam.api.repository.TeamRepository;
import io.welfareteam.api.resource.TeamModel;
import io.welfareteam.api.resource.assembler.TeamModelAssembler;

@RestController
@RequestMapping("/v1/teams")
public class TeamController {

	@Autowired
	private TeamModelAssembler assembler;
	
	@Autowired
	private TeamRepository repository;
	
	@RequestMapping(method = RequestMethod.GET)
	public PagedModel<TeamModel> getAllTeams(Pageable page) {
		
		Page<Team> teams = repository.findAll(page);
		
		PagedResourcesAssembler<Team> pageAssembler =  new PagedResourcesAssembler<>(null, null);
		
		return pageAssembler.toModel(teams, assembler);
	}
	
	
	@RequestMapping(path= "/{id}", method = RequestMethod.GET)
	public TeamModel getTeam(@PathVariable("id") Long id) {
		
		Team team = repository.findById(id).get();
		return assembler.toModel(team);
	}

	@RequestMapping(path= "", method = RequestMethod.POST)
	public TeamModel createTeam(@RequestBody Team body) {
		
		Team team = repository.saveAndFlush(body);
		return assembler.toModel(team);
	}

}
