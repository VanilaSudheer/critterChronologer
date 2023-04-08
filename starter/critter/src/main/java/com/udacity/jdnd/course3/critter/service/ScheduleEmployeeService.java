package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleEmployee;
import com.udacity.jdnd.course3.critter.repository.*;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ScheduleEmployeeService {
    @Autowired
    ScheduleSkillRepository scheduleSkillRepository;

    @Autowired
    SchedulePetRepository schedulePetRepository;

    @Autowired
    ScheduleEmployeeRepository scheduleEmployeeRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Set<ScheduleEmployee> addMultiple(ScheduleDTO scheduleDTO, Schedule schedule) {

        Set<ScheduleEmployee> empList = new HashSet<ScheduleEmployee>();

        if(null != scheduleDTO.getEmployeeIds()) {
            for(Long empIds : scheduleDTO.getEmployeeIds()) {
                Employee emp = employeeRepository.findById(empIds.longValue());
                ScheduleEmployee schedEmp = new ScheduleEmployee();
                schedEmp.setEmployee(emp);
                schedEmp.setSchedule(schedule);
                ScheduleEmployee savedScEmp = scheduleEmployeeRepository.save(schedEmp);
                empList.add(savedScEmp);

            }
        }

        if(empList.size() == 0) {
            empList = null;
        }

        return empList;
    }
    public List<Long> getEmployeeIdsByScheduleId(Long scheduleId){

        Schedule schedule = scheduleRepository.findById(scheduleId.longValue());

        List<ScheduleEmployee> empList = scheduleEmployeeRepository.findScheduleEmployeesBySchedule(schedule);
        List<Long> empIds = new ArrayList<Long>();

        for(ScheduleEmployee scEmp : empList) {

            empIds.add(scEmp.getEmployee().getId());

        }

        return empIds;

    }

    public List<ScheduleEmployee> getScheduleForEmployee(long empId){

        Employee employee = employeeRepository.findById(empId);

        return scheduleEmployeeRepository.findScheduleEmployeesByEmployee(employee);

    }
}
