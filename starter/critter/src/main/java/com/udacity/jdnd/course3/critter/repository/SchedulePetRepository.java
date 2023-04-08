package com.udacity.jdnd.course3.critter.repository;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.SchedulePet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SchedulePetRepository extends CrudRepository<SchedulePet,Long> {
    List<SchedulePet> findSchedulePetsBySchedule(Schedule schedule);
    List<SchedulePet> findSchedulePetsByPet(Pet pet);
    @Query(value = "SELECT id FROM schedule_pet WHERE pet_id in (SELECT id FROM pet WHERE customer_id = :cusId)", nativeQuery = true)

    ArrayList<Long> findSchedulePetsByCustomerId(@Param("cusId") Long cusId);
}


