package io.welfareteam.api.resource;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.welfareteam.api.common.MoodLevel;

@Relation(collectionRelation = "items")
public class MoodModel extends RepresentationModel<MoodModel> {

	private Long		id;

	@JsonIgnore
	private UserModel	user;

	private Long		userId;

	private MoodLevel	level;

	private String		comment;

	private Date		day;

	public MoodModel() {
		super();
	}

	public MoodModel(Link initialLink) {
		super(initialLink);
	}

	public MoodModel(List<Link> initialLinks) {
		super(initialLinks);
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public MoodLevel getLevel() {
		return level;
	}

	public void setLevel(MoodLevel level) {
		this.level = level;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
