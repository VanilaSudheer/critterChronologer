package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    //@ToString.Exclude
    private Set<EmployeeSkillModel> skills;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    //@ToString.Exclude
    private Set<EmployeeDayAvailable> daysAvailable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkillModel> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkillModel> skills) {
        this.skills = skills;
    }

    public Set<EmployeeDayAvailable> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<EmployeeDayAvailable> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Set<DayOfWeek> getDaysAvailableSetOnly() {
        Set<DayOfWeek> dowList = new HashSet<DayOfWeek>();

        if(null == this.getDaysAvailable()){
            return null;
        }
        for(EmployeeDayAvailable eda: this.getDaysAvailable()){
            dowList.add(eda.getDaysAvailable());
        }
        return dowList;
    }

    public Set<EmployeeSkill> getSkillsSetOnly() {
        Set<EmployeeSkill> esList = new HashSet<EmployeeSkill>();

        if(null == this.getSkills()){
            return null;
        }
        for(EmployeeSkillModel es: this.getSkills()){
            esList.add(es.getEmployeeSkill());
        }
        return esList;
    }

}
