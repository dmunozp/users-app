/**
 * 
 */
package com.devs4j.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.entities.Address;
import com.devs4j.users.entities.Profile;
import com.devs4j.users.repositories.AddressRepository;
import com.devs4j.users.repositories.ProfileRepository;

/**
 * @author dmunpalo
 *
 */
@Service
public class AddressService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private AddressRepository addressRepository;

	public List<Address> findAddressByProfileAndUser(Integer userId, Integer profileId) {
		return addressRepository.findByProfileId(userId, profileId);
	}

	public Address createAddress(Integer userId, Integer profileId, Address address) {
		Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
		if (result.isPresent()) {
			address.setProfile(result.get());
			return addressRepository.save(address);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Profile %d and user %d doesn't exists", profileId, userId));
		}
	}
}
