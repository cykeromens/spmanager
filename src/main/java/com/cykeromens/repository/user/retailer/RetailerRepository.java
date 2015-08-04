/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.repository.user.retailer;

import com.cykeromens.model.user.retailer.Retailer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author cykeromens
 */
public interface RetailerRepository extends PagingAndSortingRepository<Retailer, Long>{

    @Query("SELECT c FROM  Retailer c WHERE c.status=:status")
    public Collection<Retailer> findInactiveRetailers(@Param("status") int status);

    @Query("SELECT c FROM  Retailer c WHERE c.businessName=:businessName")
    public Optional<Retailer> findByBusinessName(@Param("businessName") String businessName);



//    @Query("select r from Retailer r where r.businessName like:businessName")
//    public Page<Retailer> searchByBusinessNameLike(@Param("businessName") String businessName, Pageable pageable);
//    @Query("select r from Retailer r where r.name like:name")
//    public Page<Retailer> searchByBusinessOwnerLike(@Param("name") String name, Pageable pageable);
//    @Query("select r from Retailer r where r.contactInfo.businessPhoneNumber like:businessPhone")
//    public Page<Retailer> searchByBusinessPhoneLike(@Param("businessPhone")String businessPhone, Pageable pageable);
//    @Query("select r from Retailer r where r.contactInfo.address.area.name like:area")
//    public Page<Retailer> searchByAreaLike(@Param("area") String area, Pageable pageable);
//    @Query("select r from Retailer r where r.createdDate like:createdDate")
//    public Page<Retailer> searchByDateOfDataLike(@Param("createdDate")String createdDate, Pageable pageable);
//    @Query("select r from Retailer r where r.category like:category")
//    public Page<Retailer> searchByCategoryLike(@Param("category")String category, Pageable pageable);
//    @Query("select r from Retailer r where r.comments like:comments")
//    public Page<Retailer> searchByCommentsLike(@Param("comments") String comments, Pageable pageable);
//    @Query("select r from Retailer r where r.contactInfo.email like:email")
//    public Page<Retailer> searchByEmailLike(@Param("email") String email, Pageable pageable);
//    @Query("select r from Retailer r where r.salesRep.firstName like:salesRep")
//    public Page<Retailer> searchBySalesRepLike(@Param("salesRep") String comments, Pageable pageable);
//    @Query("select r from Retailer r where r.contactInfo.address.street like:street")
//    public Page<Retailer> searchByStreetLike(@Param("street") String street, Pageable pageable);
//    @Query("select r from Retailer r where r.createdDate like:createdDate")
//    public Page<Retailer> searchByDateLike(@Param("createdDate") String createdDate, Pageable pageable);
//    @Query("select COUNT(c.id) FROM Retailer c WHERE c.salesRep.username=:username")
//    public Long countNumberOfRetailers(@Param("username")String username);




}
