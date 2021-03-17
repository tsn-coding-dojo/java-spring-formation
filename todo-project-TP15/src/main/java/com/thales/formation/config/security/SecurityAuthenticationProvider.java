package com.thales.formation.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.thales.formation.model.User;
import com.thales.formation.repository.UserRepository;
import com.thales.formation.service.SecurityService;

/**
 * Default application Authentication Provider. <br>
 * In case of error only one type of error is return to client application.
 */
@Service
public class SecurityAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityService securityService;

	@Override
	public Authentication authenticate(Authentication auth) {
		
		Optional<User> optUserPe = userRepository.findByLogin(auth.getName());
		
		if (!optUserPe.isPresent()) {
			throw new AuthenticationServiceException("KO");
		}
		
		User userPe = optUserPe.get();
		if (!securityService.verifyUserPassword(userPe, auth.getCredentials().toString())) {
			throw new AuthenticationServiceException("KO");
		}
		
		return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), getAutority(userPe));
	}

	private List<GrantedAuthority> getAutority(User userPe) {
		List<GrantedAuthority> a = new ArrayList<>();
		userPe.getRoles().forEach(role -> {
				a.add(new SimpleGrantedAuthority(role.getName()));
				for (String authority : role.getAuthorities()) {
					a.add(new SimpleGrantedAuthority(authority));
				}
			});
		return a;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
