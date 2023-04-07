package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleEmployee;
import com.udacity.jdnd.course3.critter.model.SchedulePet;
import com.udacity.jdnd.course3.critter.model.ScheduleSkill;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Component
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    SchedulePetService schedulePetService;
    @Autowired
    ScheduleSkillService scheduleSkillService;
    @Autowired
    ScheduleEmployeeService scheduleEmployeeService;
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO){

        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());

        scheduleRepository.save(schedule);

        schedule.setSkills(scheduleSkillService.addMultiple(scheduleDTO, schedule));

        schedule.setPets(schedulePetService.addMultiple(scheduleDTO, schedule));

        schedule.setEmployees(scheduleEmployeeService.addMultiple(scheduleDTO, schedule));

        Schedule savedSchdule = scheduleRepository.save(schedule);


        return toDTO(savedSchdule);

    }
    public List<ScheduleDTO> getAllSchedules(){

        Iterator<Schedule> allSchedList = scheduleRepository.findAll().iterator();
        List<ScheduleDTO> returnList = new ArrayList<ScheduleDTO>();

        while(allSchedList.hasNext()) {

            Schedule schedule = allSchedList.next();

            ScheduleDTO schedDTO = new ScheduleDTO();
            schedDTO.setDate(schedule.getDate());

            schedDTO.setActivities(scheduleSkillService.getSkillByScheduleId(schedule.getId()));
            schedDTO.setEmployeeIds(scheduleEmployeeService.getEmployeeIdsByScheduleId(schedule.getId()));
            schedDTO.setPetIds(schedulePetService.getPetIdsByScheduleId(schedule.getId()));

            returnList.add(schedDTO);
        }

        return returnList;

    }
    public List<ScheduleDTO> getScheduleForPet(long petId) {
        Set<Schedule> scheds = new HashSet<Schedule>();

        for(SchedulePet spet : schedulePetService.getScheduleForPet(petId)) {
            scheds.add(spet.getSchedule());
        }

        Iterator<Schedule> schedIterator = scheds.iterator();

        List<ScheduleDTO> returnList = new ArrayList<ScheduleDTO>();
        while(schedIterator.hasNext()) {
            Schedule tmpSched = schedIterator.next();
            returnList.add( toDTO(tmpSched) );
        }

        return returnList;
    }

    public List<ScheduleDTO> getScheduleForEmployee(Long empId){

        Set<Schedule> scheds = new HashSet<Schedule>();

        for(ScheduleEmployee spet : scheduleEmployeeService.getScheduleForEmployee(empId)) {
            scheds.add(spet.getSchedule());
        }

        Iterator<Schedule> schedIterator = scheds.iterator();

        List<ScheduleDTO> returnList = new ArrayList<ScheduleDTO>();
        while(schedIterator.hasNext()) {
            Schedule tmpSched = schedIterator.next();
            returnList.add( toDTO(tmpSched) );
        }

        return returnList;

    }

    public List<ScheduleDTO> getScheduleForCustomer(Long custId){

        Set<Schedule> scheds = new HashSet<Schedule>();

        for(SchedulePet spet : schedulePetService.getScheduleForCustomer(custId.longValue())) {
            scheds.add(spet.getSchedule());
        }

        Iterator<Schedule> schedIterator = scheds.iterator();

        List<ScheduleDTO> returnList = new ArrayList<ScheduleDTO>();
        while(schedIterator.hasNext()) {
            Schedule tmpSched = schedIterator.next();
            returnList.add( toDTO(tmpSched) );
        }

        return returnList;

    }


    private ScheduleDTO toDTO(Schedule entity) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());

        List<Long> petIds = new ArrayList<Long>();

        if(null != entity.getPets()) {
            for(SchedulePet pet : entity.getPets()) {
                petIds.add(pet.getPet().getId());
            }
        }

        dto.setPetIds(petIds);

        List<Long> empIds = new ArrayList<Long>();

        if(null != entity.getEmployees()) {
            for(ScheduleEmployee schedEmp : entity.getEmployees()) {
                empIds.add(schedEmp.getEmployee().getId());
            }
        }

        dto.setEmployeeIds(empIds);

        Set<EmployeeSkill> skillIds = new HashSet<EmployeeSkill>();

        if(null != entity.getSkills()) {
            for(ScheduleSkill schedSkill : entity.getSkills()) {
                skillIds.add(schedSkill.getEmployeeSkill());
            }
        }

        dto.setActivities(skillIds);

        return dto;
    }



}

