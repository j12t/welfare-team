package io.welfareteam.api.authentication;

import java.io.Serializable;

@SuppressWarnings("serial")
public class JwtResponse implements Serializable {
	
	private final String		jwttoken;

	private final Long		expiresIn;
	
	public JwtResponse(String jwttoken, long expiresIn) {
		this.jwttoken = jwttoken;
		this.expiresIn = expiresIn;
	}

	public String getToken() {
		return this.jwttoken;
	}

	
	public Long getExpiresIn() {
	
		return expiresIn;
	}
}