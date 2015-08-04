/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.contact;

import com.cykeromens.model.DomainEntity;
import com.cykeromens.model.location.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_ADDRESS")
public class Address extends DomainEntity{

    @Column(name = "STREET_NUMBER")
    private String streetNumber;
    @Column(name = "STREET")
    private String street;
    @ManyToOne( targetEntity = City.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY")
    private City city;
    @ManyToOne(targetEntity = Area.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "AREA")
    private Area area;
    @ManyToOne(targetEntity = State.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "STATE")
    private State state;
    @ManyToOne(targetEntity = Zone.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "ZONE")
    private Zone zone;
    @ManyToOne(targetEntity = Country.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY")
    private Country country;

    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    } 

    @Override
    public String toString() {
        return streetNumber+ " "+street + " " +city.getName()+"," +area.getName() + ","+ state.getName() + ","+ zone.getName()+","  + country.getName() ;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
    
    
}
