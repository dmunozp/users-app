/**
 * 
 */
package com.devs4j.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.entities.Profile;
import com.devs4j.users.entities.User;
import com.devs4j.users.repositories.ProfileRepository;
import com.devs4j.users.repositories.UserRepository;

/**
 * @author dmunpalo
 *
 */
@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
		return profileRepository.findByUserIdAndProfileId(userId, profileId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("Profile not found for user %d and profile id %d", userId, profileId)));
	}

	public Profile createProfile(Integer userId, Profile profile) {
		Optional<User> findById = userRepository.findById(userId);
		if (findById.isPresent()) {
			profile.setUser(findById.get());
			return profileRepository.save(profile);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("User id %d doesn't  exists", userId));
		}
	}
}
