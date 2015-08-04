/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.model.location;

import com.cykeromens.model.DomainEntity;

import javax.persistence.*;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_CITY")
public class City extends DomainEntity{

    @Column(name = "NAME")
    private String name; 
    @Column(name = "DESCRIPTION",length = 1500)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="J_AREA_CITIES",
        joinColumns = {@JoinColumn(name="CITY_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="AREA_ID", referencedColumnName="id")}
    )
    private Area area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    
}
