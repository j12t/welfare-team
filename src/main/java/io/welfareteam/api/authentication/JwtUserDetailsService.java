package io.welfareteam.api.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.welfareteam.api.config.UserToken;
import io.welfareteam.api.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.welfareteam.api.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByLogin(username).get();
		
		if (user != null) {
			List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
			for (String role : user.getRoles()) {
				roles.add(new SimpleGrantedAuthority(role));
			}
			return new UserToken(user, user.getLogin(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with username: " + username);
	}
	
}