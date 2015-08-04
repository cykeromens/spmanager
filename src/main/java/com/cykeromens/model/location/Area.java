/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.location;

import com.cykeromens.model.DomainEntity;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author cykeromens
 */

@Entity
@Table(name = "T_AREA")
public class Area extends DomainEntity{
    
    @Column(name = "NAME") 
    @NotNull @Size(min = 3)
    private String name;
    @Column(name = "DESCRIPTION",length = 1500)
    private String description;
    @JoinTable(name="J_STATE_AREAS",
        joinColumns = {@JoinColumn(name="AREA_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="STATE_ID", referencedColumnName="id")}
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private State state;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="J_AREA_CITIES",
        joinColumns = {@JoinColumn(name="AREA_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="CITY_ID", referencedColumnName="id")}
    )
    private Collection<City> cities;
    
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Collection<City> getCities() {
        return cities;
    }

    public void setCities(Collection<City> cities) {
        this.cities = cities;
    }
    
    
}
