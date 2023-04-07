package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
@Entity
public class ScheduleSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private EmployeeSkill employeeSkill;

    @ManyToOne
    @JoinColumn(name="schedule_id", nullable=true)
    private Schedule schedule;

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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}


