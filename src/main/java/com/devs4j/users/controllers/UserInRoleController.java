/**
 * 
 */
package com.devs4j.users.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.entities.User;
import com.devs4j.users.services.UserInRoleService;

/**
 * @author dmunpalo
 *
 */
@RestController
@RequestMapping("/roles")
public class UserInRoleController {

	@Autowired
	UserInRoleService service;

	private static final Logger log = LoggerFactory.getLogger(UserInRoleController.class);

	@GetMapping("/{roleName}/users")
	public ResponseEntity<List<User>> getUsersByRole(@PathVariable("roleName") String roleName) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name {} ", authentication.getName());
		log.info("Principal {} ", authentication.getPrincipal());
		log.info("Credentials {} ", authentication.getCredentials());
		log.info("Roles {} ", authentication.getAuthorities().toString());
		return new ResponseEntity<List<User>>(service.getUsersByRole(roleName), HttpStatus.OK);
	}

}
