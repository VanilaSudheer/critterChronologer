package com.udacity.jdnd.course3.critter.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
//@Data
public class EmployeeDayAvailable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private DayOfWeek daysAvailable;
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

    public DayOfWeek getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(DayOfWeek daysAvailable) {
        this.daysAvailable = daysAvailable;
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
        return "EmployeeDayAvailable{" +
                "id=" + id +
                ", daysAvailable=" + daysAvailable +
                ", employeeId=" + employee.getId() +
                '}';
    }

 */
}
