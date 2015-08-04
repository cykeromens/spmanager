/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.user.retailer;


import com.cykeromens.model.user.Role;
import com.cykeromens.model.user.User;
import com.cykeromens.model.user.employee.Employee;
import com.cykeromens.model.user.retailer.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author cykeromens
 */

@Entity 
@Table(name = "T_RETAILER")
public class Retailer extends User{

    @Column(name = "BUSINESS_NAME", unique = true)
    private String businessName;

    @Column(name = "SIGNED_UP_DATE", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime signedUpDate;
    
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "CSE_ID")
    private Employee salesRep;
    
    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
    

//
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name="RETAILER_STORES",
//        joinColumns = {@JoinColumn(name="RETAILER_ID", referencedColumnName="id")},
//        inverseJoinColumns = {@JoinColumn(name="STORE_ID", referencedColumnName="id")}
//    )
//    private List<Store> stores;


    @JoinColumn(name="LAST_MODIFIED")
    @JsonIgnore
    private Employee lastModifiedBy;
    @JsonIgnore
    @Column(name = "APPROVED",nullable = false)
    private boolean approved;
    @Column(name = "ACTIVITY",nullable = false)
    @JsonIgnore
    private boolean active;
    @Column(name="ON_BOARDING", nullable = false)
    @JsonIgnore
    private boolean onBoarding;
    @Column(name = "STATUS")
    private int status;

    public Retailer(String title, String firstName, String lastName, String username, String password, String email, String businessPhoneNumber) {
        super(title, firstName, lastName, username, password, email, businessPhoneNumber);
        setRole(Role.RETAILER);
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public LocalDateTime getSignedUpDate() {
        return signedUpDate;
    }

    public void setSignedUpDate(LocalDateTime signedUpDate) {
        this.signedUpDate = signedUpDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Employee getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(Employee salesRep) {
        this.salesRep = salesRep;
    }
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public Employee getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Employee lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isOnBoarding() {
        return onBoarding;
    }

    public void setOnBoarding(boolean onBoarding) {
        this.onBoarding = onBoarding;
    }


}
