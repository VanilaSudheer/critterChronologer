package com.udacity.jdnd.course3.critter.repository;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleSkillRepository extends CrudRepository<ScheduleSkill,Long> {
    List<ScheduleSkill> findScheduleSkillsBySchedule(Schedule schedule);

}

