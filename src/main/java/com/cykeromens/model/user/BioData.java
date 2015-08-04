/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.user;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author cykeromens
 */
@Embeddable
public class BioData implements Serializable {
    
    private String gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    private LocalDateTime dateOfBirth;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}
