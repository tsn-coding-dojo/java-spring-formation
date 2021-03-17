package com.thales.formation.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thales.formation.model.User;

public interface UserRepository extends PagingAndSortingRepository<User , Long> {

	Optional<User> findByLogin(String login);
	
}
