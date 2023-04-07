package com.udacity.jdnd.course3.critter.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private long id;
    @OneToMany(fetch = FetchType.LAZY,mappedBy="schedule", cascade = CascadeType.ALL)
    private Set<ScheduleEmployee> employees;
    @OneToMany(fetch = FetchType.LAZY,mappedBy="schedule", cascade = CascadeType.ALL)
    private Set<SchedulePet> pets;

    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="schedule", cascade = CascadeType.ALL)
    private Set<ScheduleSkill> skills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ScheduleEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<ScheduleEmployee> employees) {
        this.employees = employees;
    }

    public Set<SchedulePet> getPets() {
        return pets;
    }

    public void setPets(Set<SchedulePet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<ScheduleSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<ScheduleSkill> skills) {
        this.skills = skills;
    }
}


