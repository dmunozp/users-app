/**
 * 
 */
package com.devs4j.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.models.User;
import com.devs4j.users.services.UserServiceUsingLists;

/**
 * @author dmunpalo
 *
 */
@RestController
// Definición de mi recurso
@RequestMapping("/v1/users")
public class UserControllerUsingLists {

	// Inyección de mi servicio
	@Autowired
	private UserServiceUsingLists userService;

//	@RequestMapping(method = RequestMethod.GET)
//	@GetMapping
	// Método HTTP + Recurso -> Handler methods
//	public ResponseEntity<List<User>> getUsers() {
//		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
//	}

	@GetMapping
	public ResponseEntity<List<User>> getUsers(@RequestParam(value = "startWith", required = false) String startWith) {
		return new ResponseEntity<List<User>>(userService.getUsers(startWith), HttpStatus.OK);
	}

	@GetMapping(value = "/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{username}")
	public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user) {
		return new ResponseEntity<User>(userService.udpateUser(user, username), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
		userService.deleteUser(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
