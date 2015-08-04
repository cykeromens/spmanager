/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.department;

import com.cykeromens.model.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name="T_DEPARTMENT")
public class Department extends DomainEntity{
    
    @Column(name = "DEPARTMENT_NAME", nullable = false, unique = true)
    private String name;
    @Column(name = "OBJECTIVE")
    private String objective;
    @Column(name = "DESCRIPTION")
    private String description;

    public Department(String name, String objective, String description) {
        this.name = name;
        this.objective = objective;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
