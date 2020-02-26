package io.welfareteam.api.controller;

import java.util.ArrayList;
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

import io.welfareteam.api.entity.Team;
import io.welfareteam.api.entity.User;
import io.welfareteam.api.repository.TeamRepository;
import io.welfareteam.api.repository.UserRepository;
import io.welfareteam.api.resource.TeamModel;
import io.welfareteam.api.resource.assembler.TeamModelAssembler;

@RestController
@CrossOrigin
@RequestMapping("/v1/teams")
public class TeamController {

	@Autowired
	private TeamModelAssembler	assembler;

	@Autowired
	private TeamRepository		teamRepository;

	@Autowired
	private UserRepository		userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public PagedModel<TeamModel> getAllTeams(Pageable page) {

		Page<Team> teams = teamRepository.findAll(page);

		PagedResourcesAssembler<Team> pageAssembler = new PagedResourcesAssembler<>(null, null);

		return pageAssembler.toModel(teams, assembler);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public TeamModel getTeam(@PathVariable("id") Long id) {

		Team team = teamRepository.findById(id).get();
		return assembler.toModel(team);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public TeamModel createTeam(@RequestBody TeamModel teamModel) {

		Team team = new Team();
		team.setName(teamModel.getName());

		if (teamModel.getAdmins() != null) {
			team.setAdmins(new ArrayList<User>());
			for (Long id : teamModel.getAdmins()) {
				try {
					User user = userRepository.findById(id).get();
					team.getAdmins().add(user);
				} catch (NoSuchElementException e) {
					throw new NoSuchElementException("User with id " + id + " not found");
				}
			}
		}

		team = teamRepository.saveAndFlush(team);
		return assembler.toModel(team);
	}

}
