package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.SchedulePet;
import com.udacity.jdnd.course3.critter.repository.*;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class SchedulePetService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleSkillRepository scheduleSkillRepository;

    @Autowired
    SchedulePetRepository schedulePetRepository;

    @Autowired
    ScheduleEmployeeRepository scheduleEmployeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Set<SchedulePet> addMultiple(ScheduleDTO scheduleDTO, Schedule schedule) {

        Set<SchedulePet> allList = new HashSet<SchedulePet>();

        if(null != scheduleDTO.getPetIds()) {
            for(Long petId : scheduleDTO.getPetIds()) {
                Pet pet = petRepository.findById(petId.longValue());
                SchedulePet schedPet = new SchedulePet();
                schedPet.setPet(pet);
                schedPet.setSchedule(schedule);
                schedulePetRepository.save(schedPet);
                allList.add(schedPet);

            }
        }

        if(allList.size() == 0) {
            allList = null;
        }

        return allList;
    }
    public List<Long> getPetIdsByScheduleId(Long scheduleId){

        Schedule schedule = scheduleRepository.findById(scheduleId.longValue());

        List<SchedulePet> petList = schedulePetRepository.findSchedulePetsBySchedule(schedule);
        List<Long> petIds = new ArrayList<Long>();

        for(SchedulePet esm : petList) {

            petIds.add(esm.getPet().getId());

        }

        return petIds;

    }

    public List<SchedulePet> getScheduleForPet(long petId) {
        Pet pet = petRepository.findById(petId);
        return schedulePetRepository.findSchedulePetsByPet(pet);
    }
    public List<SchedulePet> getScheduleForCustomer(long customerId){

        List<Long> schedPetIds = schedulePetRepository.findSchedulePetsByCustomerId(customerId);

        List<SchedulePet> schedLists = new ArrayList<>();

        for(Long id : schedPetIds) {
            SchedulePet schedulePet = schedulePetRepository.findById(id).get();

            schedLists.add(schedulePet);
        }

        return schedLists;

    }

}
