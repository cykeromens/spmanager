/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.service.store.product;

import com.cykeromens.model.store.products.Product;
import com.cykeromens.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author cykeromens
 */
public interface ProductService extends Service {

    public Page<Product> productBySubCategory(Long subCatId, Pageable pageable);
}
