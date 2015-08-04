/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.user;

import com.cykeromens.model.DomainEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author cykeromens
 */

public enum Role{

    ADMIN,STAFF,USER,RETAILER,
    
}
