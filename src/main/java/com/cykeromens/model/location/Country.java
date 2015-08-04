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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_COUNTRY")
public class Country extends DomainEntity{

    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    
    @JoinTable(name="J_COUNTRY_PROVINCE",
        joinColumns = {@JoinColumn(name="COUNTRY_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="ZONE_ID", referencedColumnName="id")}
    )
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Zone> zones;

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

    public Collection<Zone> getZones() {
        return zones;
    }

    public void setZones(Collection<Zone> zones) {
        this.zones = zones;
    }
    
    
}
