package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    Set<Pet> findPetsByOwner(Customer customer);
    Pet findById(long id);
}
