package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
//@Data
public class EmployeeSkillModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private EmployeeSkill employeeSkill;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    //@ToString.Exclude
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeeSkill getEmployeeSkill() {
        return employeeSkill;
    }

    public void setEmployeeSkill(EmployeeSkill employeeSkill) {
        this.employeeSkill = employeeSkill;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /*
    @Override
    public String toString() {
        return "EmployeeSkillModel{" +
                "id=" + id +
                ", employeeSkill=" + employeeSkill +
                '}';
    }

 */
}
