package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleSkill;
import com.udacity.jdnd.course3.critter.repository.*;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ScheduleSkillService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleSkillRepository scheduleSkillRepository;

    @Autowired
    SchedulePetRepository schedulePetRepository;

    @Autowired
    ScheduleEmployeeRepository scheduleEmployeeRepository;

    @Autowired
    EmployeeSkillRepository employeeSkillRepository;

    public Set<ScheduleSkill> addMultiple(ScheduleDTO scheduleDTO, Schedule schedule) {

        Set<ScheduleSkill> allList = new HashSet<ScheduleSkill>();

        if(null != scheduleDTO.getActivities()) {
            for(EmployeeSkill skill : scheduleDTO.getActivities()) {

                ScheduleSkill schedSkill = new ScheduleSkill();
                schedSkill.setEmployeeSkill(skill);
                schedSkill.setSchedule(schedule);
                scheduleSkillRepository.save(schedSkill);
                allList.add(schedSkill);

            }
        }

        if(allList.size() == 0) {
            allList = null;
        }

        return allList;
    }
    public Set<EmployeeSkill> getSkillByScheduleId(Long scheduleId){

        Schedule schedule = scheduleRepository.findById(scheduleId.longValue());

        List<ScheduleSkill> empList = scheduleSkillRepository.findScheduleSkillsBySchedule(schedule);
        Set<EmployeeSkill> empSkill = new HashSet<EmployeeSkill>();

        for(ScheduleSkill esm : empList) {

            empSkill.add(esm.getEmployeeSkill());

        }

        return empSkill;

    }

}


