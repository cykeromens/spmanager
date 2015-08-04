/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.repository.location;

import com.cykeromens.model.location.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author cykeromens
 */
public interface ZoneRepository extends JpaRepository<Zone, Long>{
    
//    @Query("SELECT c FROM T_RETAILER where c.zone =:zone")
//    public Collection<Retailer> findRetailerByZone(@Param("zone") String zone);
}
