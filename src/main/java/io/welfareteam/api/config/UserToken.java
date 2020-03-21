package io.welfareteam.api.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import io.welfareteam.api.entity.User;

@SuppressWarnings("serial")
public class UserToken extends org.springframework.security.core.userdetails.User {

	private User user;
	
	public UserToken(User user, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
