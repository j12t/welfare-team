package io.welfareteam.api.entity;

import javax.persistence.Entity;

//@Entity
public class Mood {

	private User user;

	private Mood mood;
	
	private String comment;

	public Mood() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Mood getMood() {
		return mood;
	}

	public void setMood(Mood mood) {
		this.mood = mood;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
