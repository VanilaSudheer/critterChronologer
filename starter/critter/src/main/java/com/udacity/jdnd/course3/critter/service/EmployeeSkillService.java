package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkillModel;
import com.udacity.jdnd.course3.critter.repository.EmployeeSkillRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class EmployeeSkillService {
    @Autowired
    EmployeeSkillRepository employeeSkillRepository;
    public Set<EmployeeSkillModel> addMultipleSkills(EmployeeDTO employeeDTO, Employee employee) {

        Set<EmployeeSkillModel> esmList = new HashSet<EmployeeSkillModel>();

        if(null != employeeDTO.getSkills()) {
            for(EmployeeSkill es : employeeDTO.getSkills()) {

                EmployeeSkillModel esmTemp = new EmployeeSkillModel();
                esmTemp.setEmployee(employee);
                esmTemp.setEmployeeSkill(es);
                employeeSkillRepository.save(esmTemp);
                esmList.add(esmTemp);

            }
        }

        if(esmList.size() == 0) {
            esmList = null;
        }

        return esmList;
    }

}
