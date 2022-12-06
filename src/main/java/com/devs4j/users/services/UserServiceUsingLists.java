/**
 * 
 */
package com.devs4j.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.models.User;
import com.github.javafaker.Faker;

/**
 * @author dmunpalo
 *
 */
@Service
public class UserServiceUsingLists {

	@Autowired
	private Faker faker;

	private List<User> users = new ArrayList<>();

	@PostConstruct
	public void init() {
		for (int i = 0; i < 100; i++) {
			users.add(new User(faker.funnyName().name(), faker.name().username(), faker.dragonBall().character()));
		}

	}

//	public List<User> getUsers() {
//		return users;
//	}

	public List<User> getUsers(String startWith) {
		if (startWith == null)
			return users;
		return users.stream().filter(u -> u.getUserName().startsWith(startWith)).collect(Collectors.toList());
	}
	
	public User getUserByUsername(String username) {
		return users.stream().filter(u -> u.getUserName().equals(username)).findAny()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
						String.format("User %s not foud", username)));
	}

	public User createUser(User user) {
		if(users.stream().anyMatch(u->u.getUserName().equals(user.getUserName()))) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, 
					String.format("User %s ready exists", user.getUserName()));
		} 
		users.add(user);
		return user;
	}
	
	public User udpateUser(User user, String username) {
		User userToBeUpdate = getUserByUsername(username);
		userToBeUpdate.setNickName(user.getNickName());
		userToBeUpdate.setPassword(user.getPassword());
		return userToBeUpdate;
	}
	
	public void deleteUser(String username) {
		User userByUsername = getUserByUsername(username);
		users.remove(userByUsername);
	}
	
}
