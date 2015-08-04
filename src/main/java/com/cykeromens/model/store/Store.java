/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.store;


import com.cykeromens.model.DomainEntity;
import com.cykeromens.model.contact.ContactDetails;
import com.cykeromens.model.store.category.Category;
import com.cykeromens.model.store.products.Product;
import com.cykeromens.model.user.retailer.Retailer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author cykeromens
 *
 *
 */

@Entity
@Table(name = "T_STORE")
public class Store extends DomainEntity{


    @Column(name = "NAME", unique = true)
    @Size(min = 4)
    private String storeName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="J_RETAILER_PRODUCTS",
        joinColumns = {@JoinColumn(name="RETAILER_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="PRODUCT_ID", referencedColumnName="id")}
    )
    private Collection<Product> products;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinTable(name="J_STORE_CATEGORIES",
            joinColumns = {@JoinColumn(name="STORE_ID", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="CATEGORY_ID", referencedColumnName="id")}
    )
    private Collection<Category> categories;


    @JsonBackReference
    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name="RETAILER_STORES",
        joinColumns = {@JoinColumn(name="STORE_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="RETAILER_ID", referencedColumnName="id")}
    )
    private Retailer retailer;

    public Store(String storeName, Retailer retailer) {
        this.storeName = storeName;
        this.retailer = retailer;
    }


    public Store() {
        
    }


    @Transient
    public String getCategoryString() {
        if (categories != null){
            StringBuilder stringBuilder = new StringBuilder();
            int catCount = categories.size();
            for (Category cat : categories) {
                stringBuilder.append(cat.getName());
                catCount--;
                if(catCount >0){
                    stringBuilder.append(", ");
                }
            }
            return stringBuilder.toString();
        }
        return null;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailerAccount(Retailer retailer) {
        this.retailer = retailer;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }
}
