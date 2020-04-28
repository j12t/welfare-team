package io.welfareteam.api.resource.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.welfareteam.api.controller.TeamController;
import io.welfareteam.api.entity.MailSetting;
import io.welfareteam.api.resource.MailSettingModel;

@Component
//TODO J12T : never used
public class MailSettingModelAssembler extends RepresentationModelAssemblerSupport<MailSetting, MailSettingModel> {

	public MailSettingModelAssembler() {
		super(TeamController.class, MailSettingModel.class);
	}
	
	@Override
	public MailSettingModel toModel(MailSetting entity) {
		MailSettingModel model = new MailSettingModel();
		model.setLanguage(entity.getLanguage());
		model.setSendingTime(entity.getSendingTime());
		model.setMonday(entity.isMonday());
		model.setTuesday(entity.isTuesday());
		model.setWednesday(entity.isWednesday());
		model.setThursday(entity.isThursday());
		model.setFryday(entity.isFriday());
		model.setSaturday(entity.isSaturday());
		model.setSunday(entity.isSunday());
		
		model.add(linkTo(methodOn(TeamController.class).getTeam(entity.getId())).slash("mailSetting").withSelfRel());
		return model;
	}

}
