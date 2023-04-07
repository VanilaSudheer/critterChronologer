package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeDayAvailable;
import com.udacity.jdnd.course3.critter.model.EmployeeSkillModel;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeSkillRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.*;

@Service
@Component
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeSkillService employeeSkillService;
    @Autowired
    EmployeeDayAvailableService employeeDayAvailableService;
    @Autowired
    EmployeeSkillRepository employeeSkillRepository;

    public EmployeeDTO addEmployee(EmployeeDTO dto) {
        Employee savedEmployee = employeeRepository.save(toEntity(dto));
        return toDTO(savedEmployee);
    }

    public EmployeeDTO findById(long employeeId) {
        Employee emp = employeeRepository.findById(employeeId);
        return toDTO(emp);
    }
    public void setDaysAvailable(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee emp = employeeRepository.findById(employeeId);
        EmployeeDTO edto = toDTO(emp);
        edto.setDaysAvailable(daysAvailable);

        Set<EmployeeDayAvailable> edaList = employeeDayAvailableService
                .addMultipleDays(edto, emp);

        emp.setDaysAvailable(edaList);

        employeeRepository.save(emp);
    }
    public List<EmployeeDTO> getAllEmployeeForService(EmployeeRequestDTO employeeReqDTO) {
        Iterator<EmployeeSkill> empsk =  employeeReqDTO.getSkills().iterator();
        List<EmployeeDTO> allList = new ArrayList<EmployeeDTO>();

        ArrayList<Long> esmodel = new ArrayList<Long>();

        while(empsk.hasNext()) {
            EmployeeSkill tempempsk = empsk.next();
            esmodel.add(Long.valueOf(tempempsk.ordinal()));
        }


        ArrayList<Long> empList = employeeSkillRepository.findEmployeeSkillModel(esmodel, esmodel.size());

        Set<Employee> emList = new HashSet<Employee>();

        for(Long empid: empList) {

            emList.add(employeeRepository.findById(empid.longValue()));

        }


        Iterator<Employee> emIterator = emList.iterator();

        while(emIterator.hasNext()) {

            Employee tmpEmp = emIterator.next();
            allList.add(toDTO(tmpEmp));

        }

        return allList;

    }




    private  Employee toEntity(EmployeeDTO dto){
        Employee employee = new Employee();
        //employee
        employee.setName(dto.getName());
        employee = employeeRepository.save(employee);

        //employeeSkill
        Set<EmployeeSkillModel> empSkills = employeeSkillService
                .addMultipleSkills(dto, employee);
        employee.setSkills(empSkills);

        //employeeDayAvailable
        Set<EmployeeDayAvailable> edaList = employeeDayAvailableService
                .addMultipleDays(dto, employee);

        employee.setDaysAvailable(edaList);
        return employee;

    }
    private EmployeeDTO toDTO(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDaysAvailable(entity.getDaysAvailableSetOnly());
        dto.setSkills(entity.getSkillsSetOnly());

        return dto;
    }

}
