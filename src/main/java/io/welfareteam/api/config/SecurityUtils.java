package io.welfareteam.api.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import io.welfareteam.api.entity.User;

public final class SecurityUtils {

	public static final User getLogguedUser() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null)
			return null;
		return ((UserToken) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
	}
	
	public static final boolean isAdmin() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
	}
	
	public static final boolean isUser() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("USER"));
	}

}
