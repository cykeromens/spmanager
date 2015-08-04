/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.store.category;

import com.cykeromens.model.DomainEntity;
import com.cykeromens.model.store.products.Product;

import javax.persistence.*;
import java.util.Collection;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_SUB_CATEGORY")
public class SubCategory extends DomainEntity{
    
    @Column(name = "SUB_CATEGORY_NAME", unique = true)
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="J_CATEGORY_SUBCATEGORIES",
        joinColumns = {@JoinColumn(name="SUBCATEGORY_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="CATEGORY_ID", referencedColumnName="id")}
    )
    private Category category;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name="J_SUBCATEGORY_PRODUCTS",
        joinColumns = {@JoinColumn(name="SUBCATEGORY_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="PRODUCT_ID", referencedColumnName="id")}
    )
    private Collection<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProduct(Collection<Product> products) {
        this.products = products;
    }
    
    
    
}
