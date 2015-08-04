/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.model.location;

import com.cykeromens.model.DomainEntity;

import javax.persistence.*;
import java.util.Collection;

/**
 *
 * @author cykeromens
 */
@Entity 
@Table(name = "T_ZONE")
public class Zone extends DomainEntity{
    
    @Column(name = "NAME",unique = true)
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="J_COUNTRY_PROVINCE",
        joinColumns = {@JoinColumn(name="ZONE_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="STATE_ID", referencedColumnName="id")}
    )
    private Collection<State> states;
    
    @JoinTable(name="J_COUNTRY_ZONES",
        joinColumns = {@JoinColumn(name="ZONE_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="COUNTRY_ID", referencedColumnName="id")}
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;


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

    public Collection<State> getStates() {
        return states;
    }

    public void setStates(Collection<State> states) {
        this.states = states;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
    
    
    
}
