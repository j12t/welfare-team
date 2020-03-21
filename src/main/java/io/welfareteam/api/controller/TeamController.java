package io.welfareteam.api.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import io.welfareteam.api.config.SecurityUtils;
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

	@GetMapping
	public PagedModel<TeamModel> getAllTeams(Pageable page) {

		Page<Team> teams = teamRepository.findAll(page);

		PagedResourcesAssembler<Team> pageAssembler = new PagedResourcesAssembler<>(null, null);

		return pageAssembler.toModel(teams, assembler);
	}

	@GetMapping(path = "/{id}")
	public TeamModel getTeam(@PathVariable("id") Long id) {

		Team team = teamRepository.findById(id).get();
		return assembler.toModel(team);
	}

	@PostMapping
	public TeamModel createTeam(@RequestBody TeamModel teamModel) {

		if (!SecurityUtils.isAdmin()) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}

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
