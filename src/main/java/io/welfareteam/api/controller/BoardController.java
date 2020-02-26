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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import io.welfareteam.api.entity.Board;
import io.welfareteam.api.entity.Team;
import io.welfareteam.api.repository.BoardRepository;
import io.welfareteam.api.repository.TeamRepository;
import io.welfareteam.api.resource.BoardModel;
import io.welfareteam.api.resource.TeamModel;
import io.welfareteam.api.resource.assembler.BoardModelAssembler;
import io.welfareteam.api.resource.assembler.TeamModelAssembler;

@RestController
@CrossOrigin
@RequestMapping("/v1/boards")
public class BoardController {

	@Autowired
	private BoardModelAssembler	boardAssembler;

	@Autowired
	private TeamModelAssembler	teamAssembler;

	@Autowired
	private BoardRepository		boardRepository;

	@Autowired
	private TeamRepository		teamRepository;

	@RequestMapping(method = RequestMethod.GET)
	public PagedModel<BoardModel> getAllBoards(Pageable page) {

		Page<Board> boards = boardRepository.findAll(page);

		PagedResourcesAssembler<Board> pageAssembler = new PagedResourcesAssembler<>(null, null);

		return pageAssembler.toModel(boards, boardAssembler);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public BoardModel getBoard(@PathVariable("id") Long id) {

		Board board = boardRepository.findById(id).get();
		return boardAssembler.toModel(board);
	}

	@RequestMapping(path = "/{id}/teams", method = RequestMethod.GET)
	public PagedModel<TeamModel> getTeamsByBoardId(Pageable page, @PathVariable("id") Long id) {

		Page<Team> teams = teamRepository.findByBoardId(page, id);

		PagedResourcesAssembler<Team> pageAssembler = new PagedResourcesAssembler<>(null, null);

		return pageAssembler.toModel(teams, teamAssembler);
	}

	@RequestMapping(method = RequestMethod.POST)
	public BoardModel createBoard(@RequestBody BoardModel boardModel) {

		if (boardModel == null || boardModel.getName() == null || boardModel.getName().isEmpty())
			throw HttpClientErrorException.create(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
		
		Board board = new Board();
		board.setName(boardModel.getName());
		
		if (boardModel.getTeamIds() != null) {
			board.setTeams(new ArrayList<Team>());
			
			for (Long teamId : boardModel.getTeamIds()) {
				try {
					Team team = teamRepository.findById(teamId).get();
					board.getTeams().add(team);
				} catch (NoSuchElementException e) {
					throw new NoSuchElementException("Team with id " + teamId + " not found");
				}
			}
		}
		
		boardRepository.save(board);
		return boardAssembler.toModel(board);
	}

}
