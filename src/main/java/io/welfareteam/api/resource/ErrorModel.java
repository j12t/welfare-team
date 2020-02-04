package io.welfareteam.api.resource;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ErrorModel  extends RepresentationModel<ErrorModel> {

	private String code;

	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private String attribute;
	
	public ErrorModel() {
		super();
	}

	public ErrorModel(Link initialLink) {
		super(initialLink);
	}

	public ErrorModel(List<Link> initialLinks) {
		super(initialLinks);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

}