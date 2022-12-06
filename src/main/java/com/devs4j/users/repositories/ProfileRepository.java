/**
 * 
 */
package com.devs4j.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.users.entities.Profile;

/**
 * @author dmunpalo
 *
 */
@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer>{

}
