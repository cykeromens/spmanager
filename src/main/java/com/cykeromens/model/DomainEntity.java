/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.model;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 *
 * @author cykeromens
 */
@MappedSuperclass
public class DomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Version @Column(name = "VERSION")
    private int version;

    @Column(name = "CREATED_DATE", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime lastModifiedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DomainEntity)){
            return false;
        }
        DomainEntity other = (DomainEntity) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Transient
    public String getLastModifiedDateString() {
        String lastModifiedDateString;
        if (lastModifiedDate != null){
            lastModifiedDateString = lastModifiedDate.toLocalDate().toString();
            return lastModifiedDateString;
        }
        return null;
    }

    @Transient
    public String getCreatedDateString() {
        String createdDateString;
        if (createdDate != null){
            createdDateString = createdDate.toLocalDate().toString();
            return createdDateString;
        }
        return null;
    }



    @Override
    public String toString() {
        return "com.spacepointe.model.DomainEntity[ id=" + id + " ]";
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
