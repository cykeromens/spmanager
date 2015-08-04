/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.user.retailer.account;


import com.cykeromens.model.DomainEntity;
import com.cykeromens.model.user.retailer.Retailer;
import com.cykeromens.web.util.StringBuilderUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_ACCOUNT")
public class Account extends DomainEntity{
    
    @Column(name = "ACCOUNT_NUMBER",unique = true)
    private long accountNumber;
    @Column(name = "PIN_NUMBER")
    private int pinNumber;
    @Column(name = "BACK_OFFICE_URL")
    private String backOfficeUrl;

    public Account() {
    }
    
    public Account(Retailer retailer) {
            StringBuilderUtil stringBuilderUtil = new StringBuilderUtil();
            long parseAccountNumber = Long.parseLong(stringBuilderUtil.generateAccountNumber());
            setAccountNumber(parseAccountNumber);
            setPinNumber(stringBuilderUtil.generatePin(6));
            setBackOfficeUrl("spr-"+retailer.getBusinessName());
    }    
    
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public  void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    
    public String getBackOfficeUrl() {
        return backOfficeUrl;
    }

    public void setBackOfficeUrl(String backOfficeURL) {
        this.backOfficeUrl = backOfficeURL;
    }
    
}
