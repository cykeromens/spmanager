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

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_STATES")
public class State extends DomainEntity{
    @Column(name = "NAME", unique = true)
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="J_STATE_AREAS",
        joinColumns = {@JoinColumn(name="STATE_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="AREA_ID", referencedColumnName="id")}
    )
    private Collection<Area> areas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="J_ZONE_STATES",
        joinColumns = {@JoinColumn(name="STATE_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="ZONE_ID", referencedColumnName="id")}
    )
    private Zone zone;

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

    public Collection<Area> getAreas() {
        return areas;
    }

    public void setAreas(Collection<Area> areas) {
        this.areas = areas;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
    
    
}
