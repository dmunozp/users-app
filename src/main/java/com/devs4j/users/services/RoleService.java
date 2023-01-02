/**
 * 
 */
package com.devs4j.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.entities.Role;
import com.devs4j.users.models.AuditDetails;
import com.devs4j.users.repositories.RoleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dmunpalo
 *
 */
@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	private ObjectMapper mapper = new ObjectMapper();

	public List<Role> getRoles() {
		return repository.findAll();
	}

//	public Role createRole(Role role) {
//		return repository.save(role);
//	}

	public Role createRole(Role role) {
		Role save = repository.save(role);

		AuditDetails details = new AuditDetails(SecurityContextHolder.getContext().getAuthentication().getName(),
				role.getName());
		try {
			kafkaTemplate.send("devs4j-topic", mapper.writeValueAsString(details));
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error parsing the message");
		}

		return save;
	}

	public Role updateRole(Integer roleId, Role role) {
		Optional<Role> result = repository.findById(roleId);
		if (result.isPresent()) {
			return repository.save(role);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Role id %d doesn't  exists", roleId));
		}
	}

	public void deleteRole(Integer roleId) {
		Optional<Role> result = repository.findById(roleId);
		if (result.isPresent()) {
			repository.delete(result.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Role id %d doesn't  exists", roleId));
		}
	}

}
