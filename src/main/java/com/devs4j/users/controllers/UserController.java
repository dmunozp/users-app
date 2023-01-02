/**
 * 
 */
package com.devs4j.users.controllers;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.entities.User;
import com.devs4j.users.services.UserService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * @author dmunpalo
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	@Timed("get.users")
	public ResponseEntity<Page<User>> getUsers(
			@RequestParam(required = false, value = "page", defaultValue = "0") int page,
			@RequestParam(required = false, value = "size", defaultValue = "1000") int size) {
//		return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
		return new ResponseEntity<>(service.getUsers(page, size), HttpStatus.OK);
	}

	@GetMapping("/usernames")
	public ResponseEntity<Page<String>> getUsernames(
			@RequestParam(required = false, value = "page", defaultValue = "0") int page,
			@RequestParam(required = false, value = "size", defaultValue = "1000") int size) {
		return new ResponseEntity<>(service.getUsernames(page, size), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	@ApiOperation(value = "Returns a user for a given user id", response = User.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "The record was found"),
			@ApiResponse(code = 404, message = "The record was not found") 
	})
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<>(service.getUserById(userId), HttpStatus.OK);
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<>(service.getUserByUsername(username), HttpStatus.OK);
	}

	@PostMapping // No es lo más correcto. POST se usa para crear. Ya que al autenticar se
					// mandarían
					// varias cosas por URL con Pathparam, si no te gusta, lo haces con POST -> PERO
					// NO CON JSON SINO CON FORMDATA
	public ResponseEntity<User> authenticate(@RequestBody User user) {
		return new ResponseEntity<>(service.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()),
				HttpStatus.OK);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
		service.deleteUserByUsername(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
