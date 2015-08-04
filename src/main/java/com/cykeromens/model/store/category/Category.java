/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.store.category;

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
@Table(name = "T_CATEGORY")
public class Category extends DomainEntity{
    
    @Column(name = "CATEGORY_NAME")
    private String name;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="J_CATEGORY_SUBCATEGORIES",
            joinColumns = {@JoinColumn(name="CATEGORY_ID", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="SUBCATEGORY_ID", referencedColumnName="id")}
    )
    private Collection<SubCategory> subCategories;

    @Column(name = "DESCRIPTION")
    private String description;
    
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Collection<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}