/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.user;

import com.cykeromens.model.user.retailer.Retailer;
import com.cykeromens.web.form.user.RetailerEditForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.Optional;


/**
 *
 * @author cykeromens
 */
public interface RetailerService extends UserService{
    
    public Optional<Retailer> findByBusinessName(String businessName);
    public Page<Retailer> findAllByPage(Pageable pageable);
    public Optional<Retailer> create(RetailerEditForm retailerEditForm);
    public void scheduleStatusTask();


//    public Page<Retailer> searchByBusinessName(String searchItem, Pageable pageable);
//    public Page<Retailer> searchByBusinessOwner(String searchItem, Pageable pageable);
//    public Page<Retailer> searchByBusinessPhone(String searchItem, Pageable pageable);
//    public Page<Retailer> searchByArea(String searchItem, Pageable pageable);
//    public Page<Retailer> searchByDateOfData(String searchItem, Pageable pageable);
//    public Page<Retailer> searchByCategory(String searchItem, Pageable pageable);
//    public Page<Retailer> searchByComments(String searchItem, Pageable pageable);
//    public Page<Retailer> searchByEmail(String searchItem, Pageable pageable);
//    public Page<Retailer> searchBySalesRep(String searchItem, Pageable pageable);
//    public Page<Retailer> searchByDateCreated(String searchItem, Pageable pageable);
//    public Page<Retailer> searchByStreet(String searchItem, Pageable pageable);
//    public void scheduleStatusTask();
//    public long findAreaSize(String area);
//    public Retailer findByRetailerAccNumber(Long accNumber);
//    public Page<Retailer> searchByDateLike(String searchItem, Pageable pageable);
//    public Long countRetailersInArea(String username);
//    public Collection<Retailer> getAllRetailers(int startPosition, int stopPosition);

}
