package io.welfareteam.api.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MAIL_SETTING")
public class MailSetting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long	id;

	private String	language;

	@Basic
	@Temporal(TemporalType.TIME)
	private Date	sendingTime;

	private boolean	monday;

	private boolean	tuesday;

	private boolean	wednesday;

	private boolean	thursday;

	private boolean	friday;

	private boolean	saturday;

	private boolean	sunday;

	public MailSetting() {

		super();
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
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

	public boolean isFriday() {

		return friday;
	}

	public void setFriday(boolean friday) {

		this.friday = friday;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (friday ? 1231 : 1237);
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + (monday ? 1231 : 1237);
		result = prime * result + (saturday ? 1231 : 1237);
		result = prime * result + ((sendingTime == null) ? 0 : sendingTime.hashCode());
		result = prime * result + (sunday ? 1231 : 1237);
		result = prime * result + (thursday ? 1231 : 1237);
		result = prime * result + (tuesday ? 1231 : 1237);
		result = prime * result + (wednesday ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailSetting other = (MailSetting) obj;
		if (friday != other.friday)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (monday != other.monday)
			return false;
		if (saturday != other.saturday)
			return false;
		if (sendingTime == null) {
			if (other.sendingTime != null)
				return false;
		} else if (!sendingTime.equals(other.sendingTime))
			return false;
		if (sunday != other.sunday)
			return false;
		if (thursday != other.thursday)
			return false;
		if (tuesday != other.tuesday)
			return false;
		if (wednesday != other.wednesday)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MailSetting [id=" + id + ", language=" + language + ", sendingTime=" + sendingTime + ", monday=" + monday + ", tuesday=" + tuesday + ", wednesday=" + wednesday + ", thirsday="
				+ thursday + ", friday=" + friday + ", saturday=" + saturday + ", sunday=" + sunday + "]";
	}

}
