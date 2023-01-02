/**
 * 
 */
package com.devs4j.users.services;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.devs4j.users.entities.User;
import com.devs4j.users.models.Devs4jSecurityRule;
import com.devs4j.users.repositories.UserInRoleRepository;

/**
 * @author dmunpalo
 *
 */
@Service
//@Devs4jSecurityRule // Si se considera aplicar una anotación de seguridad a todos los métodos, es preferible definirla a nivel de clase
public class UserInRoleService {

	@Autowired
	private UserInRoleRepository repository;
	
	private static final Logger log = LoggerFactory.getLogger(UserInRoleService.class);

//	@Secured("ROLE_ADMIN") // Pertenece a Spring
//	@RolesAllowed("ROLE_ADMIN") // No pertenece a Spring, no hay dependencia con el framework
//	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//	@PostAuthorize("hasRole('ROLE_ADMIN')")
	@Devs4jSecurityRule
	public List<User> getUsersByRole(String roleName) {
		log.info("Getting roles by name {} ", roleName);
		return repository.findUsersByRoleName(roleName);
	}

}
