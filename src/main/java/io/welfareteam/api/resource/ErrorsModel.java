package io.welfareteam.api.resource;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class ErrorsModel  extends RepresentationModel<ErrorsModel> {

	private List<ErrorModel> errors;

	public ErrorsModel() {
		super();
	}

	public ErrorsModel(Link initialLink) {
		super(initialLink);
	}

	public ErrorsModel(List<Link> initialLinks) {
		super(initialLinks);
	}

	public List<ErrorModel> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorModel> errors) {
		this.errors = errors;
	}

}