/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.repository.store.category;

import com.cykeromens.model.store.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author cykeromens
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
