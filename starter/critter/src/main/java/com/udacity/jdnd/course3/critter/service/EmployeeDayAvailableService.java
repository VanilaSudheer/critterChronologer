package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeDayAvailable;
import com.udacity.jdnd.course3.critter.repository.EmployeeDayAvailableRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;
@Component
public class EmployeeDayAvailableService {
    @Autowired
    EmployeeDayAvailableRepository employeeDayAvailableRepository;
    public Set<EmployeeDayAvailable> addMultipleDays(EmployeeDTO employeeDTO, Employee employee) {

        Set<EmployeeDayAvailable> edaList = new HashSet<EmployeeDayAvailable>();

        if(null != employeeDTO.getDaysAvailable()) {
            for(DayOfWeek dayOfWeek : employeeDTO.getDaysAvailable()) {

                EmployeeDayAvailable edaTemp = new EmployeeDayAvailable();
                edaTemp.setEmployee(employee);
                edaTemp.setDaysAvailable(dayOfWeek);
                employeeDayAvailableRepository.save(edaTemp);

                edaList.add(edaTemp);

            }
        }

        if(edaList.size() == 0) {
            edaList = null;
        }

        return edaList;
    }

}
