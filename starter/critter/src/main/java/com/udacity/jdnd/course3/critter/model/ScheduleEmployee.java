package com.udacity.jdnd.course3.critter.model;

import javax.persistence.*;
@Entity
public class ScheduleEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=true)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="schedule_id", nullable=true)
    private Schedule schedule;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}


