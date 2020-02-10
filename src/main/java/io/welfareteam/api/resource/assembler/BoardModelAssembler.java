package io.welfareteam.api.resource.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.welfareteam.api.controller.BoardController;
import io.welfareteam.api.entity.Board;
import io.welfareteam.api.entity.Team;
import io.welfareteam.api.resource.BoardModel;

@Component
public class BoardModelAssembler extends RepresentationModelAssemblerSupport<Board, BoardModel> {
	
	public BoardModelAssembler() {
		super(BoardController.class, BoardModel.class);
	}
	
	@Override
	public BoardModel toModel(Board entity) {
		
		BoardModel model = new BoardModel();
		model.setName(entity.getName());
		
		List<Long> teamIds  = new ArrayList<Long>();
		if (entity.getTeams() != null) {
			for (Team team : entity.getTeams()) {
				teamIds.add(team.getId());
			}
			model.setTeamIds(teamIds);
		}
		
		model.add(linkTo(methodOn(BoardController.class).getBoard(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(BoardController.class).getTeamsByBoardId(null, entity.getId())).withRel("teams"));
		
		return model;
	}

}
