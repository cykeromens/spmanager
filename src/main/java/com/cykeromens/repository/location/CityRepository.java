/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.repository.location;


import com.cykeromens.model.location.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author cykeromens
 */
public interface CityRepository extends JpaRepository<City, Long> {
    
}
