/**
 * 
 */
package com.devs4j.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.users.entities.Address;

/**
 * @author dmunpalo
 *
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
