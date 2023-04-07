package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleEmployeeRepository extends CrudRepository<ScheduleEmployee,Long> {
    List<ScheduleEmployee> findScheduleEmployeesBySchedule(Schedule schedule);
    List<ScheduleEmployee> findScheduleEmployeesByEmployee(Employee employee);
}


