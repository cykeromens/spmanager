/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.user.employee;

import com.cykeromens.model.department.Department;
import com.cykeromens.model.user.User;
import com.cykeromens.web.form.user.EmployeeEditForm;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_EMPLOYEE")
public class Employee extends User{

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;


    public Employee() {
    }

    public Employee(EmployeeEditForm ef) {
        super(ef.getTitle(),ef.getFirstName(),ef.getLastName(),ef.getUsername(),
                ef.getPassword(),ef.getEmail(), ef.getPhoneNumber() );
        setRole(ef.getRole());
    }

    @Transient
    public String getDateOfEmploymentString() {
        String birthDateString = "";
        if (dateOfEmployment != null)
            birthDateString = dateOfEmployment.toLocalDate().toString();
        return birthDateString;
    }

    @Column(name = "DATE_OF_EMPLOYMENT", nullable = false)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDateTime dateOfEmployment;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDateTime getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDateTime dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    
}
