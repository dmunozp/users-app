/**
 * 
 */
package com.devs4j.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.entities.User;
import com.devs4j.users.repositories.UserRepository;

/**
 * @author dmunpalo
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Page<User> getUsers(int page, int size) {
		return repository.findAll(PageRequest.of(page, size));		
//		return repository.findAll();
	}
	
	public Page<String> getUsernames(int page, int size) {
		return repository.findbyUsernames(PageRequest.of(page, size));		
//		return repository.findbyUsernames();		

	}

	public User getUserById(Integer userId) {
		return repository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User Id %d not found", userId)));
	}

	public User getUserByUsername(String username) {
		return repository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("User name %s not found", username)));
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("User name %s and password %s not found", username, password)));
	}
}
