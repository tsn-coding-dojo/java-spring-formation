package com.thales.formation.service;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thales.formation.enums.Role;
import com.thales.formation.model.User;
import com.thales.formation.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityService securityService;

	@PostConstruct
	public void postConstruct() {
		// For demo purpose only !
		
		log.info("Creating users !");
		if (!userRepository.findByLogin("admin").isPresent()) {
			User userPe1 = new User();
			userPe1.setLogin("admin");
			securityService.setPassword(userPe1, "admin");
			userPe1.getRoles().add(Role.ADMIN);
			userPe1.getRoles().add(Role.USER);
			userRepository.save(userPe1);
		}

		if (!userRepository.findByLogin("user").isPresent()) {

			User userPe2 = new User();
			userPe2.setLogin("user");
			securityService.setPassword(userPe2, "user");
			userPe2.getRoles().add(Role.USER);
			userRepository.save(userPe2);
		}
	}
	
	@PreDestroy
	public void preDestroy() {
		log.info("Goodbye !");
	}
	
	public User getCurrentUser() {
		Optional<User> optUserPe = userRepository.findByLogin(securityService.getAuthenticationUserLogin());
		if (!optUserPe.isPresent()) {
			return null;
		}
		return optUserPe.get();
	}

}
