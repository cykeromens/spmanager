/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.store.products;

import com.cykeromens.model.DomainEntity;
import com.cykeromens.model.image.Image;
import com.cykeromens.model.store.category.SubCategory;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_PRODUCT")
public class Product extends DomainEntity{

    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "DESCRIPTION")
    private String productDescription;

    @JoinTable(name="J_SUBCATEGORY_PRODUCTS",
        joinColumns = {@JoinColumn(name="PRODUCT_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="SUB_CATEGORY_ID", referencedColumnName="id")}
    )
    @ManyToOne(fetch = FetchType.EAGER)
    private SubCategory subcategory;
    @Column(name="SALE_PRICE")
    private double salePrice;
    @Column(name="COST_PRICE") 
    private double costPrice;
    @Column(name="TAX")
    private double tax;
    @Column(name ="PRODUCT_CODE")
    private String productCode;
    @Column(name="QUICK_PICK_GROUP")
    private String quickPickGroup;
    @Column(name = "MANUFACTURER")
    private String manufacturer;
    @Column(name="NUMBER_OF_ITEMS")
    private int totalNumberOfItems;
    @Column(name = "RE_ORDER_POINT")
    private String reorderPoint;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="J_PRODUCT_IMAGES",
        joinColumns = {@JoinColumn(name="PRODUCT_ID", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="IMAGE_ID", referencedColumnName="id")}
    )
    private Collection<Image> images;

    @Transient
    private List<MultipartFile> files;
    @Basic(fetch=FetchType.LAZY)
    @Lob @Column(name = "PHOTO")
    private byte[] photo;
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getQuickPickGroup() {
        return quickPickGroup;
    }

    public void setQuickPickGroup(String quickPickGroup) {
        this.quickPickGroup = quickPickGroup;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getTotalNumberOfItems() {
        return totalNumberOfItems;
    }

    public void setTotalNumberOfItems(int totalNumberOfItems) {
        this.totalNumberOfItems = totalNumberOfItems;
    }

    public String getReorderPoint() {
        return reorderPoint;
    }

    public void setReorderPoint(String reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    public Collection<Image> getImages() {
        return images;
    }

    public void setImages(Collection<Image> images) {
        this.images = images;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    
    
}
