/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model.image;

import com.cykeromens.model.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author cykeromens
 */
@Entity
@Table(name = "T_IMAGES")
public class Image extends DomainEntity{
    
    @Column(name="IMAGE_NAME")
    private String imageName;
    @Column(name="IMAGE_URL")
    private String imageUrl;
    @Column(name = "IMAGE_SIZE")
    private byte imageSize;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getImageSize() {
        return imageSize;
    }

    public void setImageSize(byte imageSize) {
        this.imageSize = imageSize;
    }
    
}
