package io.welfareteam.api.resource;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.hateoas.RepresentationModel;

public class MailSettingModel extends RepresentationModel<MailSettingModel> {

	private String	language;

	@Basic
	@Temporal(TemporalType.TIME)
	private Date	sendingTime;

	private boolean	monday;

	private boolean	tuesday;

	private boolean	wednesday;

	private boolean	thursday;

	private boolean	fryday;

	private boolean	saturday;

	private boolean	sunday;

	public MailSettingModel() {
		super();
	}

	
	public String getLanguage() {
		return language;
	}

	
	public void setLanguage(String language) {
		this.language = language;
	}

	
	public Date getSendingTime() {
		return sendingTime;
	}

	
	public void setSendingTime(Date sendingTime) {
		this.sendingTime = sendingTime;
	}

	
	public boolean isMonday() {
		return monday;
	}

	
	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	
	public boolean isTuesday() {
		return tuesday;
	}

	
	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}

	
	public boolean isWednesday() {
		return wednesday;
	}

	
	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	
	public boolean isThursday() {
		return thursday;
	}

	
	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	
	public boolean isFryday() {
		return fryday;
	}

	
	public void setFryday(boolean fryday) {
		this.fryday = fryday;
	}

	
	public boolean isSaturday() {
		return saturday;
	}

	
	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}

	
	public boolean isSunday() {
		return sunday;
	}

	
	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}

}
