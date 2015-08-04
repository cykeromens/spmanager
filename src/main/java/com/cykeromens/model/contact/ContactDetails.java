/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.contact;

import com.cykeromens.model.DomainEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_CONTACT_DETAILS")
public class ContactDetails extends DomainEntity{
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="J_RETAILER_ADDRESS",
        joinColumns = {@JoinColumn(name="RETAILER_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="ADDRESS_ID", referencedColumnName="id")}
    )
    private Address address;
    @Column(name = "BUSINESS_NUMBER")
    private String businessPhoneNumber;
    @Column(name = "MOBILE_NUMBER")
    private String mobilePhoneNumber;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name="PREFERRED_CONTACT")
    private String preferredModeOfContact;
 
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferredModeOfContact() {
        return preferredModeOfContact;
    }

    public void setPreferredModeOfContact(String preferredModeOfContact) {
        this.preferredModeOfContact = preferredModeOfContact;
    }
    
}
