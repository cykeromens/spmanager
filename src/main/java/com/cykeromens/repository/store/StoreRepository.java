/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.repository.store;

import com.cykeromens.model.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author cykeromens
 */
public interface StoreRepository extends JpaRepository<Store,Long>{

    @Query("select s FROM Store s WHERE s.storeName=:storeName")
    public Store findStore(@Param("storeName")String storeName);
}
